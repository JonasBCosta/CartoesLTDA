package EmbraceTI.CartoesLTDA.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

//import javax.validation.constraints.NotBlank;
import org.springframework.hateoas.ResourceSupport;

//import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
public class Transacao{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long nsu;
	
	@NotNull
	private float valor;
	
	@NotBlank
	private String bandeira;
	
	@NotBlank
	private String modalidade;
	
	@NotBlank
	private String horario;

	public long getNsu() {
		return nsu;
	}

	public void setNsu(long nsu) {
		this.nsu = nsu;
	}

	public float getValor() {
		return valor;
	}

	public void setValor(float valor) {
		this.valor = valor;
	}

	public String getBandeira() {
		return bandeira;
	}

	public void setBandeira(String bandeira) {
		this.bandeira = bandeira;
	}

	public String getModalidade() {
		return modalidade;
	}

	public void setModalidade(String modalidade) {
		this.modalidade = modalidade;
	}

	public String getHorario() {
		return horario;
	}

	public void setHorario(String horario) {
		this.horario = horario;
	}
}
