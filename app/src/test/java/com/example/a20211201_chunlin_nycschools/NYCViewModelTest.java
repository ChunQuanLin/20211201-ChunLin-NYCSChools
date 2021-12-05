package com.example.a20211201_chunlin_nycschools;

import static org.junit.Assert.assertTrue;

import android.app.Application;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mockito;

/*
* if i had more time, there would be more tests and i would refactor some classes to use dependency injections
* */
public class NYCViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    NYCViewModel nycViewModel;
    @Before
    public void setUp() throws Exception {
        nycViewModel = new NYCViewModel(Mockito.mock(Application.class));
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getListOfNYCSchools() {
        assertTrue(nycViewModel.getListOfNYCSchools().isEmpty());
    }

    @Test
    public void refreshData() {
    }
}