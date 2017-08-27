package com.example.hasnaa.orangelabstask;

import com.example.hasnaa.orangelabstask.UI.PhotosPresenter;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void searchTest (){
        final PhotosPresenter presenter = new PhotosPresenter();
        presenter.search("cat");

    }
}