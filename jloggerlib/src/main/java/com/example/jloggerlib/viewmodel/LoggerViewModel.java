package com.example.jloggerlib.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;


import com.example.jloggerlib.model.Logger;
import com.example.jloggerlib.repository.LoggerRepository;
import com.example.jloggerlib.utility.Direction;

import java.util.Date;
import java.util.List;

public class LoggerViewModel extends AndroidViewModel {

    // This variable expose the repository operation to the view model
    private final LoggerRepository loggerRepository;

    // This variable observe the state of the sorting live-data
    private final MutableLiveData<String> sortingMutableLiveData;

    // This variable observe the state of the sorting live-data
    private final MutableLiveData<String> logTextSearchMutableLiveData;

    // This variable observe the state of the sorting live-data
    private final MutableLiveData<String> logFilterTypeMutableLiveData;

    // This variable uses MediatorLiveData to combine multiple observers
    private final CustomLiveData customLiveData;

    public LoggerViewModel(@NonNull Application application) {
        super(application);
        loggerRepository = new LoggerRepository(application);
        customLiveData = new CustomLiveData(
                sortingMutableLiveData = new MutableLiveData<>()
                , logTextSearchMutableLiveData = new MutableLiveData<>(),
                logFilterTypeMutableLiveData = new MutableLiveData<>());
    }

    /**
     * Allows to insert new logger data to the persistent storage
     * The function works in the background the notify livedata observers
     * when got triggered
     *
     * @param logger
     */
    public void insert(Logger logger) {
        loggerRepository.insert(logger);
    }

    /**
     * Allows to insert multiple loggers to the persistent storage
     * The function works in the background the notify livedata observers
     * when got triggered
     *
     * @param loggers
     */
    public void insertMany(Logger... loggers) {
        loggerRepository.insertMany(loggers);
    }

    /**
     * Delete all the logs from the persistent storage
     */
    public void deleteAll() {
        loggerRepository.deleteAll();
    }

    /**
     * Delete single logs from the persistent storage
     */
    public void delete(Logger logger) {
        loggerRepository.delete(logger);
    }

    /**
     * Count the number of logs in the persistent storage
     * This method can be observed and reflect the changes in the persistent storage
     *
     * @return integer live data
     */
    public LiveData<Integer> loggerCount() {
        return loggerRepository.loggerCount();
    }

    /**
     * Get all logs in the persistent storage without pagination
     * This method can be observed and reflect the changes in the persistent storage
     *
     * @return live data of logs
     */
    public LiveData<List<Logger>> getAllLogs() {
        return loggerRepository.getAllLogs();
    }

    /**
     * Get all logs filtered by tag name
     * This method can be observed and reflect the changes in the persistent storage
     *
     * @param tag
     * @return live data of logs
     */
    public LiveData<List<Logger>> getAllLogsByTagName(String tag) {
        return loggerRepository.getAllLogsByTagName(tag);
    }

    /**
     * Get all logs greater then the date which passed as an argument sorted in descending order
     * This method can be observed and reflect the changes in the persistent storage
     *
     * @param start
     * @return live data of logs
     */
    public LiveData<List<Logger>> getAllLogsByDateGreaterThan(Date start) {
        return loggerRepository.getAllLogsByDateGreaterThan(start);
    }

    /**
     * Get all logs filtered by log message sorted in ascending order
     * This method can be observed and reflect the changes in the persistent storage
     *
     * @param filterPattern
     * @return live data of logs
     */
    public LiveData<List<Logger>> getAllLogsByMessageLike(String filterPattern) {
        return loggerRepository.getAllLogsByMessageLike(filterPattern);
    }


    public void setLogSearchValue(String value) {
        logTextSearchMutableLiveData.setValue(value);
    }

    public void setSortingValue(String direction) {
        sortingMutableLiveData.setValue(direction);
    }

    public void setLogTypeValue(String type) {
        logFilterTypeMutableLiveData.setValue(type);
    }


    /**
     * This method handle the case of change in the state of the observer
     * The class uses switch map to swap between different observers while the
     * CustomLiveData got triggered using the mediator live data.
     *
     * @return LiveData<List < Logger>>
     */
    public LiveData<List<Logger>> getByFilters() {
        return Transformations.switchMap(customLiveData, input -> {
            String sortBy = input.first == null ? Direction.ASC.name() : input.first;
            String textSearch = input.second == null ? "" : input.second;
            String filterType = input.third == null ? Logger.DEBUG : input.third;
            if (sortBy.equals(Direction.ASC.name())) {
                return filterType.equals(Logger.TRACE) ? loggerRepository.getAllLogs() : loggerRepository.getAllByTagAndFilterValueLike(Direction.ASC.name(), textSearch, filterType);
            } else {
                return filterType.equals(Logger.TRACE) ? loggerRepository.getAllLogs() : loggerRepository.getAllByTagAndFilterValueLike(Direction.DESC.name(), textSearch, filterType);
            }
        });
    }

    /**
     * This class represent customLiveData class which combine three observers
     * while one of the observers chagnes all the data is reflected the user using the getByFilters
     * switchMap.
     */
    private static class CustomLiveData extends MediatorLiveData<Triple<String, String, String>> {
        public CustomLiveData(LiveData<String> sort, LiveData<String> textSearch, LiveData<String> filterType) {
            addSource(sort, s -> setValue(Triple.create(s, textSearch.getValue(), filterType.getValue())));
            addSource(textSearch, s -> setValue(Triple.create(sort.getValue(), s, filterType.getValue())));
            addSource(filterType, s -> setValue(Triple.create(sort.getValue(), textSearch.getValue(), s)));
        }
    }
}
