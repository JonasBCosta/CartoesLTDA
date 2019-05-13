package EmbraceTI.CartoesLTDA.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import EmbraceTI.CartoesLTDA.model.Transacao;
import EmbraceTI.CartoesLTDA.repository.TransacaoRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value="API REST Cartões LTDA")
@RestController	
@RequestMapping("/transacao")
public class TransacaoResources {
	
	@Autowired // Faz injeção de dependencias automaticamente
	private TransacaoRepository tr;
	
	@ApiOperation(value="Retorna uma lista de transações")
	@GetMapping(produces="application/json") // Cria o metodo GET
	public @ResponseBody Iterable<Transacao> listaTransacoes(){
		Iterable<Transacao> listaTransacoes = tr.findAll(); // Buscará todos as transações
		return listaTransacoes;
	}
	
	@ApiOperation(value="Adiciona uma transação")
	@PostMapping() // Cria o metodo POST
	public Transacao CadastroTransacao(@RequestBody @Valid Transacao transacao){ // @Valid usará as validações para permitir o salvamento
		return tr.save(transacao); // fará somente se passar pela validação
	}
	
	@ApiOperation(value="Exclui uma transação")
	@DeleteMapping() // Cria o metodo DELETE
	public Transacao deletaTransacao(@RequestBody Transacao transacao){
		tr.delete(transacao);
		return transacao;
	}
}
