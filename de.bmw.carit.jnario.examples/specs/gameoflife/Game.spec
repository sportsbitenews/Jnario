package gameoflife

import de.bmw.carit.jnario.runner.InstantiateWith
import org.mockito.Mock

import static gameoflife.AliveCell.*
import static org.mockito.Matchers.*
import static org.mockito.Mockito.*

import static extension de.bmw.carit.jnario.lib.Should.*

@InstantiateWith(typeof(MockitoInstantiator))
describe Game{
	
	@Mock Cell cell
	
	it "keeps all alive cells"{
		val newCell = aliveCell()
		when(cell.evolve(any)).thenReturn(newCell)
		subject.evolve(newHashSet(cell)).should.contain(newCell)
	}
	
}