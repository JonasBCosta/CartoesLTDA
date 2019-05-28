package EmbraceTI.CartoesLTDA.resources;


import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

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
		// Pegar o NSU da transação e fazer alterações no BD
		if(transacao.getModalidade().equals("debito"))
			transacao.setLiquido(transacao.getValor()*0.98);
		else
			transacao.setLiquido(transacao.getValor()*0.97);
		transacao.setDisponivel(calcDisponivel(transacao.getHorario()));
		return tr.save(transacao); // fará somente se passar pela validação
	}
	
	@ApiOperation(value="Exclui uma transação")
	@DeleteMapping() // Cria o metodo DELETE
	public Transacao deletaTransacao(@RequestBody Transacao transacao){
		tr.delete(transacao);
		return transacao;
	}
	
	 public static String calcDisponivel(String data){
		 Calendar cal = Calendar.getInstance();
		 DateFormat df = DateFormat.getDateInstance(DateFormat.MEDIUM, new Locale("pt", "BR"));
	
	     cal.set(Integer.parseInt(data.substring(0, 4)),
	    		 Integer.parseInt(data.substring(5, 7))-1,
	    		 Integer.parseInt(data.substring(8, 10)));
	     df.setCalendar(cal);
	
		 for(int i=0; i < 30; i++){
			 cal.add(Calendar.DAY_OF_MONTH, 1);
			 if((cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) || (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY))
				 i--;
		 }
	
		 data = String.valueOf(cal.get(Calendar.YEAR)) + "-" + 
				String.valueOf(String.format("%02d", cal.get(Calendar.MONTH))) + "-" + 
				String.valueOf(String.format("%02d", cal.get(Calendar.DAY_OF_MONTH))) +
				data.substring(10);
		 return data;
	 }
}
