package com.example.hasnaa.orangelabstask;

import com.example.hasnaa.orangelabstask.UI.GroupsUI.GroupModel;
import com.example.hasnaa.orangelabstask.UI.GroupsUI.GroupsPresenter;
import com.example.hasnaa.orangelabstask.UI.PhotosUI.PhotoModel;
import com.example.hasnaa.orangelabstask.UI.PhotosUI.PhotosPresenter;
import com.example.hasnaa.orangelabstask.model.GroupsSearch;

import org.junit.Test;
import org.mockito.Mockito;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

//    @Test
//    public void searchTest (){
//        GroupModel groupModel = new GroupModel();
//        groupModel.searchGroups("cat");
//        assertTrue(groupModel.searchGroups("cat"));
//    }

    @Test
    public void request_Success() {

//        Singleton singleton = Singleton.getInstance();//getTesterInstance().create(APIEndpoints.class);
//
//        Call<GroupsSearch> call = singleton.getService().GroupsList(Service.API_KEY, "flickr.groups.search", "json", "1", "cat");
//
//        try {
//            Response<GroupsSearch> response = call.execute();
//            GroupsSearch authResponse = response.body();
//
//            assertTrue(response.isSuccessful());
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        PhotoModel photoModel = new PhotoModel();
//        Mockito.verify(photoModel).searchPhotos("cat");

    }




}