package com.google.sps.servlets;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** Shows all of the images uploaded to Cloud Storage. */
@WebServlet("/memes")
public class ListImagesMemes extends HttpServlet {

  @Override
  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
   response.setContentType("text/html;");
    // List all of the uploaded files.
    public static final String PROJECT_ID = "jurquidezcalvo-sps-spring21";
    public static final String BUCKET_NAME = "jurquidezcalvo-sps-spring21.appspot.com";
    Storage storage = StorageOptions.newBuilder().setProjectId(PROJECT_ID).build().getService();
    Bucket bucket = storage.get(BUCKET_NAME);
    Page<Blob> blobs = bucket.list();

    // Output <img> elements as HTML.
    for (Blob blob : blobs.iterateAll()) {
      String imgTag = String.format("<img src=\"%s\" width = \"300\"/>", blob.getMediaLink());
      response.getWriter().println(imgTag);
    }
  }
}
