package text.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// Atliekame servleto inicializaciją paveldint ...Initializer klasę
public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		// Nurodome mūsų implementuotą konfiguracinę klasę kaip parametrą
		return new Class[] { WebApplicationContextConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		// Nurodome pradžios užklausos susiejimą - request mapping'ą
		return new String[] { "/" }; // Galima pridėti /app/* - prefiksą
	}

}
