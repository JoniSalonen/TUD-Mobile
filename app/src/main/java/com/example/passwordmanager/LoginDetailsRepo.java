package com.example.passwordmanager;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class LoginDetailsRepo {

    // below line is the create a variable
    // for dao and list for all courses.
    private Dao dao;
    private LiveData<List<LoginDetailsModal>> allDetails;

    // creating a constructor for our variables
    // and passing the variables to it.
    public LoginDetailsRepo(Application application) {
        LoginDetailsDatabase database = LoginDetailsDatabase.getInstance(application);
        dao = database.Dao();
        allDetails = dao.getAllDetails();
    }

    // creating a method to insert the data to our database.
    public void insert(LoginDetailsModal model) {
        new InsertDetailsAsyncTask(dao).execute(model);
    }

    // creating a method to update data in database.
    public void update(LoginDetailsModal model) {
        new UpdateDetailsAsyncTask(dao).execute(model);
    }

    // creating a method to delete the data in our database.
    public void delete(LoginDetailsModal model) {
        new DeleteDetailsAsyncTask(dao).execute(model);
    }

    // below is the method to delete all the details.
    public void deleteAllDetails() {
        new DeleteAllDetailsAsyncTask(dao).execute();
    }

    // below method is to read all the courses.
    public LiveData<List<LoginDetailsModal>> getAllDetails() {
        return allDetails;
    }

    // we are creating a async task method to insert new details.
    private static class InsertDetailsAsyncTask extends AsyncTask<LoginDetailsModal, Void, Void> {
        private Dao dao;

        private InsertDetailsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(LoginDetailsModal... model) {
            // below line is use to insert our modal in dao.
            dao.insert(model[0]);
            return null;
        }
    }

    // we are creating a async task method to update our details.
    private static class UpdateDetailsAsyncTask extends AsyncTask<LoginDetailsModal, Void, Void> {
        private Dao dao;

        private UpdateDetailsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(LoginDetailsModal... models) {

            dao.update(models[0]);
            return null;
        }
    }

    private static class DeleteDetailsAsyncTask extends AsyncTask<LoginDetailsModal, Void, Void> {
        private Dao dao;

        private DeleteDetailsAsyncTask(Dao dao) {
            this.dao = dao;
        }

        @Override
        protected Void doInBackground(LoginDetailsModal... models) {
            dao.delete(models[0]);
            return null;
        }
    }
    private static class DeleteAllDetailsAsyncTask extends AsyncTask<Void, Void, Void> {
        private Dao dao;
        private DeleteAllDetailsAsyncTask(Dao dao) {
            this.dao = dao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            dao.deleteAllDetails();
            return null;
        }
    }
}
