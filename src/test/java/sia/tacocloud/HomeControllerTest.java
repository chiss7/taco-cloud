package sia.tacocloud;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import sia.tacocloud.controller.HomeController;

@WebMvcTest(HomeController.class) // Web test for HomeController
public class HomeControllerTest {

  @Autowired
  private MockMvc mockMvc; // Injects MockMvc

  @Test
  public void testHomePage() throws Exception {
    mockMvc.perform(get("/")) // perform a GET request for the root path
        .andExpect(status().isOk()) // expect HTTP 200 OK
        .andExpect(view().name("home")) // expect a view whose name is "home"
        .andExpect(content().string( // expect the response to contain...
            containsString("Welcome to ..."))); // the string "Welcome to..."
  }

}
