package h2;

import Exceptions.DBTaskUndoneException;
import ProjektHalmav2.App;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.util.concurrent.ExecutorService;

public class GameResult {
    private static ExecutorService executorService = App.getExecutorService();

    public GameResult() {
    }

    public void saveResult(String winPlayerName, String loserName1, String loserName2, String loserName3) throws DBTaskUndoneException {
        SwingWorker<Void,Void> task = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("PZ");
                EntityManager em = emf.createEntityManager();
                em.getTransaction().begin();
                Win win = new Win();
                win.winPlayerName = winPlayerName;
                win.loserName1 = loserName1;
                win.loserName2 = loserName2;
                win.loserName3 = loserName3;
                em.persist(win);
                em.getTransaction().commit();
                em.close();
                emf.close();
                return null;
            }


        };
        task.execute();


        if(!task.isDone()) throw new DBTaskUndoneException();


        executorService.submit(task);
    }


    public void getScoreList(Handler<WinOutput> handler) throws DBTaskUndoneException {
        SwingWorker<WinOutput,Void> task = new SwingWorker<WinOutput, Void>() {
            @Override
            protected WinOutput doInBackground() throws Exception {
                EntityManagerFactory emf = Persistence.createEntityManagerFactory("PZ");
                EntityManager em = emf.createEntityManager();
                WinOutput list =  new WinOutput(
                        em.createQuery("select w.winPlayerName from Win w").setMaxResults(10).getResultList(),
                        em.createQuery("select w.loserName1 from Win w").setMaxResults(10).getResultList(),
                        em.createQuery("select w.loserName2 from Win w").setMaxResults(10).getResultList(),
                        em.createQuery("select w.loserName3 from Win w").setMaxResults(10).getResultList());
                em.close();
                emf.close();
                return list;
            }
        };

        if(!task.isDone()) throw new DBTaskUndoneException();


//        task.setOnSucceeded(event ->{
//            handler.handle(task.getValue());
//        });
//        if(task.isDone()){
//            event ->{ handler.handle(task.getValue())};
//        }
        executorService.submit(task);
    }
}
