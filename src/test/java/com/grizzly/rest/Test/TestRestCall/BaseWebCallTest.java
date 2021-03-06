package com.grizzly.rest.Test.TestRestCall;

import com.grizzly.rest.Definitions.DefinitionsHttpMethods;
import com.grizzly.rest.EasyRestCall;
import com.grizzly.rest.GenericRestCall;
import com.grizzly.rest.Model.BaseWebCall;
import com.grizzly.rest.Test.BaseAndroidTestClass;
import com.grizzly.rest.Test.TestEntities.TestString;
import com.grizzly.rest.WebServiceFactory;
import org.apache.http.Header;
import org.apache.http.message.BasicHeader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.springframework.http.HttpStatus;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * Created by fpardo on 8/7/14.
 */
@RunWith(RobolectricTestRunner.class)
public class BaseWebCallTest extends BaseAndroidTestClass {


    @Test
    public void restTest(){

        WebServiceFactory webFactory = new WebServiceFactory();

        EasyRestCall<BaseWebCall, TestString, Void> restCall = webFactory.getRestCallInstance(BaseWebCall.class, TestString.class, true);

        BaseWebCall webCall = new BaseWebCall();

        webCall.getRestContainer().setRequestUrl("www.google.cl");
        webCall.getRestContainer().setMyHttpMethod(DefinitionsHttpMethods.METHOD_POST);

        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "{\n" +
                "    \"my_value\": \"asdf\"\n" +
                "}", headers);

        restCall.setEntity(webCall);

        try {

            System.out.println("Rest test: BaseWebCall");
            org.junit.Assert.assertTrue(restCall.execute().get());

            System.out.println("\nMy test string is:"+restCall.getJsonResponseEntity().getMyValue());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void genericRestTest(){

        Map<String, String> m = new HashMap<>();

        WebServiceFactory webFactory = new WebServiceFactory();

        GenericRestCall<String, TestString, Void> restCall = webFactory.getGenericRestCallInstance(String.class, TestString.class, true);

        String webCall = "a web call with no configuration";
        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "{\n" +
                "    \"my_value\": \"asdf\"\n" +
                "}", headers);

        restCall.setEntity(webCall);

        restCall.setUrl("www.google.cl");
        restCall.setMethodToCall(DefinitionsHttpMethods.METHOD_POST);

        try {

            System.out.println("Rest test: BaseWebCall");
            org.junit.Assert.assertTrue(restCall.execute().get());

            System.out.println("\nMy test string is:"+restCall.getJsonResponseEntity().getMyValue());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void UrlParamsTest(){

        WebServiceFactory webFactory = new WebServiceFactory();

        EasyRestCall<BaseWebCall, TestString, Void> restCall = webFactory.getRestCallInstance(BaseWebCall.class, TestString.class, true);

        BaseWebCall webCall = new BaseWebCall();

        webCall.getRestContainer().setRequestUrl("www.google.cl");
        webCall.getRestContainer().setMyHttpMethod(DefinitionsHttpMethods.METHOD_POST);
        webCall.getRestContainer().addParameterToUrl("q", "cheese is bad");
        webCall.getRestContainer().addParameterToUrl("ie", "UTF-8");

        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "{\n" +
                "    \"my_value\": \"asdf\"\n" +
                "}", headers);

        restCall.setEntity(webCall);

        try {

            System.out.println("Rest test: BaseWebCall");
            org.junit.Assert.assertTrue(restCall.execute().get());

            System.out.println("\nMy test string is:"+restCall.getJsonResponseEntity().getMyValue());
            System.out.println("\nMy url params are:"+webCall.getRestContainer().getRequestUrl());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


    @Test
    public void InheritClassTest(){

        WebServiceFactory webFactory = new WebServiceFactory();

        EasyRestCall<BaseWebCall, TestString, Void> restCall = new EasyRestCall<>(BaseWebCall.class, TestString.class, Void.class, 1);

        BaseWebCall webCall = new BaseWebCall();

        webCall.getRestContainer().setRequestUrl("www.google.cl");
        webCall.getRestContainer().setMyHttpMethod(DefinitionsHttpMethods.METHOD_POST);
        webCall.getRestContainer().addParameterToUrl("q", "cheese is bad");
        webCall.getRestContainer().addParameterToUrl("ie", "UTF-8");

        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "{\n" +
                "    \"my_value\": \"asdf\"\n" +
                "}", headers);

        restCall.setEntity(webCall);

        try {

            System.out.println("Rest test: EasyRestCall2");
            org.junit.Assert.assertTrue(restCall.execute().get());

            System.out.println("\nMy test string is:"+restCall.getJsonResponseEntity().getMyValue());
            System.out.println("\nMy url params are:"+webCall.getRestContainer().getRequestUrl());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void BodylessPostTest(){

        EasyRestCall<BaseWebCall, TestString, Void> restCall = new EasyRestCall<>(BaseWebCall.class, TestString.class, Void.class, 1);

        BaseWebCall webCall = new BaseWebCall();

        webCall.getRestContainer().setRequestUrl("www.google.cl");
        webCall.getRestContainer().setMyHttpMethod(DefinitionsHttpMethods.METHOD_POST);
        webCall.getRestContainer().addParameterToUrl("q", "cheese is bad");
        webCall.getRestContainer().addParameterToUrl("ie", "UTF-8");

        restCall.setBodyless(true);

        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "{\n" +
                "    \"my_value\": \"asdf\"\n" +
                "}", headers);

        restCall.setEntity(webCall);

        try {

            System.out.println("Rest test: BodyLess");
            org.junit.Assert.assertTrue(restCall.execute().get());

            System.out.println("\nMy test string is:"+restCall.getJsonResponseEntity().getMyValue());
            System.out.println("\nMy url params are:"+webCall.getRestContainer().getRequestUrl());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void restTestEmpty(){

        WebServiceFactory webFactory = new WebServiceFactory();

        EasyRestCall<BaseWebCall, Void, Void> restCall = webFactory.getRestCallInstance(BaseWebCall.class, Void.class, true);

        BaseWebCall webCall = new BaseWebCall();

        webCall.getRestContainer().setRequestUrl("www.google.cl");
        webCall.getRestContainer().setMyHttpMethod(DefinitionsHttpMethods.METHOD_POST);

        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "", headers);

        restCall.setEntity(webCall);

        try {

            System.out.println("Rest test: Empty Call");
            org.junit.Assert.assertTrue(restCall.execute().get());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void cacheRestTest(){

        Map<String, String> m = new HashMap<>();

        WebServiceFactory webFactory = new WebServiceFactory();

        GenericRestCall<String, TestString, Void> restCall = webFactory.getGenericRestCallInstance(String.class, TestString.class, true);

        String webCall = "a web call with no configuration";
        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "{\n" +
                "    \"my_value\": \"asdf\"\n" +
                "}", headers);

        restCall.setEntity(webCall);
        restCall.setContext(getContext());
        restCall.setUrl("www.google.cl");
        restCall.setMethodToCall(DefinitionsHttpMethods.METHOD_POST);
        restCall.setAutomaticCacheRefresh(true);

        try {

            System.out.println("Rest test: CachedWebCall: 1");
            org.junit.Assert.assertTrue(restCall.execute().get());

            System.out.println("\nMy test string is:"+restCall.getJsonResponseEntity().getMyValue());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Robolectric.clearPendingHttpResponses();
        GenericRestCall<String, TestString, Void> restCall2 = webFactory.getGenericRestCallInstance(String.class, TestString.class, true);
        restCall2.setEntity(webCall);
        restCall2.setContext(getContext());
        restCall2.setUrl("www.google.cl");
        restCall2.setMethodToCall(DefinitionsHttpMethods.METHOD_POST);

        try {

            System.out.println("Rest test: CachedWebCall: 2");
            org.junit.Assert.assertTrue(restCall2.execute().get());

            System.out.println("\nMy test string is:"+restCall2.getJsonResponseEntity().getMyValue());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void cacheRestTest2(){

        Map<String, String> m = new HashMap<>();

        WebServiceFactory webFactory = new WebServiceFactory();

        GenericRestCall<String, TestString, Void> restCall = webFactory.getGenericRestCallInstance(String.class, TestString.class, true);

        String webCall = "a web call with no configuration";
        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "{\n" +
                "    \"my_value\": \"asdf\"\n" +
                "}", headers);

        restCall.setEntity(webCall);
        restCall.setContext(getContext());
        restCall.setUrl("www.google.cl");
        restCall.setMethodToCall(DefinitionsHttpMethods.METHOD_POST);
        restCall.setFullAsync(true);
        restCall.setAutomaticCacheRefresh(true);

        try {

            System.out.println("Rest test: CachedWebCall: 1");
            org.junit.Assert.assertTrue(restCall.execute().get());

            System.out.println("\nMy test string is:"+restCall.getJsonResponseEntity().getMyValue());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Robolectric.clearPendingHttpResponses();
        GenericRestCall<String, TestString, Void> restCall2 = webFactory.getGenericRestCallInstance(String.class, TestString.class, true);
        restCall2.setEntity(webCall);
        restCall2.setContext(getContext());
        restCall2.setUrl("www.google.cl");
        restCall2.setMethodToCall(DefinitionsHttpMethods.METHOD_POST);

        try {

            System.out.println("Rest test: CachedWebCall: 2");
            org.junit.Assert.assertTrue(restCall2.execute().get());

            System.out.println("\nMy test string is:"+restCall2.getJsonResponseEntity().getMyValue());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        System.out.println("Rest test: Reuse call: ");
        restCall2.execute(true);
        restCall2.execute(true);
        restCall2.execute(true);

        System.out.println("\nMy test string is:"+restCall2.getJsonResponseEntity().getMyValue());
    }

    @Test
    public void builderTest() {

        Map<String, String> m = new HashMap<>();

        WebServiceFactory webFactory = new WebServiceFactory();

        GenericRestCall<String, TestString, Void> restCall = webFactory.getGenericRestCallInstance(String.class, TestString.class, true);

        String webCall = "a web call with no configuration";
        Header headers = new BasicHeader("Content-type", "application/json");

        Robolectric.addPendingHttpResponse(HttpStatus.OK.value(), "{\n" +
                "    \"my_value\": \"asdf\"\n" +
                "}", headers);

        restCall.setEntity(webCall);
        restCall.setContext(getContext());
        restCall.setUrl("www.google.cl");
        restCall.setMethodToCall(DefinitionsHttpMethods.METHOD_POST);
        restCall.setFullAsync(true);
        restCall.setAutomaticCacheRefresh(true);

        try {

            System.out.println("Rest test: CachedWebCall: 1");
            org.junit.Assert.assertTrue(restCall.execute().get());

            System.out.println("\nMy test string is:" + restCall.getJsonResponseEntity().getMyValue());

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
