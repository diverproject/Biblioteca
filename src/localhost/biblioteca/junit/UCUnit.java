package localhost.biblioteca.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses
({
	UC01Usuario.class,
	UC02Editora.class,
	UC03Autor.class,
	UC04Cdu.class,
	UC05Obra.class
})
public class UCUnit
{

}
