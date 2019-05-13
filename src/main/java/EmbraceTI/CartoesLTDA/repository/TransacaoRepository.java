package EmbraceTI.CartoesLTDA.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import EmbraceTI.CartoesLTDA.model.Transacao;

public interface TransacaoRepository extends JpaRepository<Transacao, String>{

//	Transacao findByCodigo(long codigo);
}
