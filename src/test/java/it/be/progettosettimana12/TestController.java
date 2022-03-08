package it.be.progettosettimana12;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TestController {

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithAnonymousUser
	public void listaAutoriWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("/api/findallAutori")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithAnonymousUser
	public void listaCategorieWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("/api/findallCategorie")).andExpect(status().isUnauthorized());
	}

	@Test
	@WithAnonymousUser
	public void listaLibriWhenUtenteIsAnonymous() throws Exception {
		this.mockMvc.perform(get("/api/findallLibri")).andExpect(status().isUnauthorized());
	}

}