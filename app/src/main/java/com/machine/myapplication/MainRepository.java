package com.machine.myapplication;

import androidx.lifecycle.MutableLiveData;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class MainRepository {

    public static MainRepository instance;

    public static MainRepository getInstance() {
        if (instance == null) {
            instance = new MainRepository();
        }
        return instance;
    }

    public MainRepository() {
        mAction = new MutableLiveData<>();
    }

    MutableLiveData<ActivityAction> mAction;

    public MutableLiveData<ActivityAction> getmAction() {
        return mAction;
    }

    public void setmAction(MutableLiveData<ActivityAction> mAction) {
        this.mAction = mAction;
    }

    public void getPosts(APIService apiService) {
        Disposable disposable1 = apiService.getPosts()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<PostData>, List<PostData>>() {
                    @Override
                    public List<PostData> apply(List<PostData> response) throws Exception {
                        // TODO - note about sort
                        return response;
                    }
                })
                .subscribeWith(new DisposableSingleObserver<List<PostData>>() {
                    @Override
                    public void onSuccess(List<PostData> response) {
                        mAction.setValue(new ActivityAction(ActivityAction.GETPOSTSSUCCESS, response,response.size()));

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mAction.setValue(new ActivityAction(ActivityAction.API_ERROR, "Timeout"));
                        } else if (e instanceof UnknownHostException) {
                            mAction.setValue(new ActivityAction(ActivityAction.API_ERROR, "No Internet"));
                        } else {
                            mAction.setValue(new ActivityAction(ActivityAction.API_ERROR, e.getMessage()));
                        }

                    }
                });
    }
   public void getUsers(APIService apiService) {
        Disposable disposable1 = apiService.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(new Function<List<UsersData>, List<UsersData>>() {
                    @Override
                    public List<UsersData> apply(List<UsersData> response) throws Exception {
                        // TODO - note about sort
                        return response;
                    }
                })
                .subscribeWith(new DisposableSingleObserver<List<UsersData>>() {
                    @Override
                    public void onSuccess(List<UsersData> response) {
                            mAction.setValue(new ActivityAction(ActivityAction.GETUSERSUCCESS, response));

                    }

                    @Override
                    public void onError(Throwable e) {
                        if (e instanceof SocketTimeoutException) {
                            mAction.setValue(new ActivityAction(ActivityAction.TIMEOUT, "Timeout"));
                        } else if (e instanceof UnknownHostException) {
                            mAction.setValue(new ActivityAction(ActivityAction.NO_INTERNET, "No Internet"));
                        } else {
                            mAction.setValue(new ActivityAction(ActivityAction.API_ERROR, e.getMessage()));
                        }

                    }
                });
    }



}
