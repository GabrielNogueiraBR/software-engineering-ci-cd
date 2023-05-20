package cleanarchitecture;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.project.cleanarchitecture.Candidato;
import com.project.cleanarchitecture.Contrato;
import com.project.cleanarchitecture.OpcaoContrato;

public class ContratoTest {
	Candidato candidato;
	Contrato contrato;
	
	@Before
	public void preparaContexto() {
		
	}
	
	@Test
	public void naoDeveSerContratadoAbaixoIdadeMinima() {
		candidato = new Candidato(14);
		contrato = new Contrato(candidato);
		
		boolean contratado = contrato.assinarContrato(OpcaoContrato.CONTRATO_PARCIAL);
		
		assertFalse(contratado);
		assertEquals(contrato.getOpcaoContrato(), OpcaoContrato.SEM_CONTRATO);
	}
	
	@Test
	public void naoDeveSerContratadoComoContratoIntegralCasoMenor() {
		candidato = new Candidato(16);
		contrato = new Contrato(candidato);
		
		boolean contratado = contrato.assinarContrato(OpcaoContrato.CONTRATO_INTEGRAL);
		
		assertFalse(contratado);
		assertEquals(contrato.getOpcaoContrato(), OpcaoContrato.SEM_CONTRATO);
	}
	
	@Test
	public void deveSerContratadoComoContratoParcialCasoMenor() {
		candidato = new Candidato(17);
		contrato = new Contrato(candidato);
		
		boolean contratado = contrato.assinarContrato(OpcaoContrato.CONTRATO_PARCIAL);
		
		assertTrue(contratado);
		assertEquals(contrato.getOpcaoContrato(), OpcaoContrato.CONTRATO_PARCIAL);
	}
	
	@Test
	public void deveSerContratadoComoContratoIntegralCasoMaior() {
		candidato = new Candidato(23);
		contrato = new Contrato(candidato);
		
		boolean contratado = contrato.assinarContrato(OpcaoContrato.CONTRATO_INTEGRAL);
		
		assertTrue(contratado);
		assertEquals(contrato.getOpcaoContrato(), OpcaoContrato.CONTRATO_INTEGRAL);
	}
	
	@Test
	public void deveSerContratadoComoContratoParcialCasoMaior() {
		candidato = new Candidato(45);
		contrato = new Contrato(candidato);
		
		boolean contratado = contrato.assinarContrato(OpcaoContrato.CONTRATO_PARCIAL);
		
		assertTrue(contratado);
		assertEquals(contrato.getOpcaoContrato(), OpcaoContrato.CONTRATO_PARCIAL);
	}
	
	@Test
	public void naoDeveSerContratadoComoContratoIntegralCasoAcimaIdadeMaxima() {
		candidato = new Candidato(58);
		contrato = new Contrato(candidato);
		
		boolean contratado = contrato.assinarContrato(OpcaoContrato.CONTRATO_INTEGRAL);
		
		assertFalse(contratado);
		assertEquals(contrato.getOpcaoContrato(), OpcaoContrato.SEM_CONTRATO);
	}
	
	@Test
	public void naoDeveSerContratadoComoContratoParcialCasoAcimaIdadeMaxima() {
		candidato = new Candidato(60);
		contrato = new Contrato(candidato);
		
		boolean contratado = contrato.assinarContrato(OpcaoContrato.CONTRATO_PARCIAL);
		
		assertFalse(contratado);
		assertEquals(contrato.getOpcaoContrato(), OpcaoContrato.SEM_CONTRATO);
	}
	

	@Test
	public void naoDeveSerContrataoCasoOpcaoContratoInvalida() {
		candidato = new Candidato(20);
		contrato = new Contrato(candidato);
		
		boolean contratado = contrato.assinarContrato(OpcaoContrato.SEM_CONTRATO);
		
		assertFalse(contratado);
		assertEquals(contrato.getOpcaoContrato(), OpcaoContrato.SEM_CONTRATO);
	}
	
	
}
