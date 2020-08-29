package br.com.steps;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Matchers;

import br.com.core.Local;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class ConsultaCepSteps {

	private String url;
	private String baseUrl = "https://viacep.com.br/ws";
	private Response response;
	private List<Local> locais = new ArrayList<Local>();
	
	public ConsultaCepSteps() {
		gerarMassa();
	}

	@Given("^o usuario insere um \"([^\"]*)\"$")
	public void oUsuarioInsereUm(String cep) throws Throwable {
		url = gerarUrl(cep);
	}

	@When("^o servico eh consultado$")
	public void oServicoEhConsultado() throws Throwable {
		response = get(url);
	}
	
	@Then("^eh retornado o \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void ehRetornadoO(String cep, String logradouro, String complemento, String bairro, String localidade, String uf, String ibge) throws Throwable {
		response.then().statusCode(200);
		response.then().body("cep", is(cep));
		response.then().body("logradouro", is(logradouro));
		response.then().body("complemento", is(complemento));
		response.then().body("bairro", is(bairro));
		response.then().body("localidade", is(localidade));
		response.then().body("uf", is(uf));
		response.then().body("ibge", is(ibge));
	}
	
	@Then("^eh retornada um atributo \"([^\"]*)\"$")
	public void ehRetornadaUmAtributo(Boolean erro) throws Throwable {
		response.then().statusCode(200);
		response.then().body("erro", is(erro));
	}

	@Then("^eh retornada uma \"([^\"]*)\"$")
	public void ehRetornadaUma(String mensagem) throws Throwable {
		response.then().statusCode(400);
		response.then().body(Matchers.containsString(mensagem));
	}
	
	@Given("^o usuário insere \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
	public void oUsuárioInsere(String uf, String localidade, String logradouro) throws Throwable {
		url = gerarUrl(uf, localidade, logradouro);
	}

	@Then("^eh retornada uma lista de locais que correspondem a pesquisa$")
	public void ehRetornadaUmaListaDeLocaisQueCorrespondemAPesquisa() throws Throwable {
		response.then().statusCode(200);
		response.then().body("$", hasSize(locais.size()));
		for (int i = 0; i < locais.size(); i++) {
			response.then().body("cep["+i+"]", is(locais.get(i).getCep()));
			response.then().body("logradouro["+i+"]", is(locais.get(i).getLogradouro()));
			response.then().body("complemento["+i+"]", is(locais.get(i).getComplemento()));
			response.then().body("bairro["+i+"]", is(locais.get(i).getBairro()));
			response.then().body("localidade["+i+"]", is(locais.get(i).getLocalidade()));
			response.then().body("uf["+i+"]", is(locais.get(i).getUf()));
			response.then().body("ibge["+i+"]", is(locais.get(i).getIbge()));
		}
	}

	private String gerarUrl(String cep) {
		return baseUrl + "/" + cep + "/json/";
	}
	
	private String gerarUrl(String uf, String localidade, String logradouro) {
		return baseUrl + "/" + uf + "/" + localidade + "/" + logradouro + "/json/";
	}
	
	private void gerarMassa() {
		locais.add(new Local("94085-170", "Rua Ari Barroso", "", "Morada do Vale I", "Gravataí", "RS", "4309209"));
		locais.add(new Local("94175-000", "Rua Almirante Barroso", "", "Recanto Corcunda", "Gravataí", "RS", "4309209"));
	}

}
