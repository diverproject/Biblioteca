package localhost.biblioteca.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses
({
	UC01Usuario.class,
	UC02Editora.class
})
public class UCUnit
{

}
