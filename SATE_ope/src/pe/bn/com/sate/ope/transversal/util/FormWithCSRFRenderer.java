package pe.bn.com.sate.ope.transversal.util;

 
 
import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.context.ResponseWriter;

import org.apache.log4j.Logger;

import com.sun.faces.renderkit.html_basic.FormRenderer;

import java.io.IOException;

public class FormWithCSRFRenderer {
	/*extends FormRenderer {
	private final Logger log = Logger
			.getLogger(FormWithCSRFRenderer.class);
    @Override
    public void encodeEnd(FacesContext context, UIComponent component) throws IOException {
        log.info("FormWithCSRFRenderer - Adding CSRF Token to form element");
        ResponseWriter writer = context.getResponseWriter();
        writer.startElement("input", component);
        writer.writeAttribute("type", "hidden", null);
        writer.writeAttribute("name", context.getExternalContext().getRequestMap().get("_csrf.parameterName"), null);
        writer.writeAttribute("value", context.getExternalContext().getRequestMap().get("_csrf.token"), null);
        writer.endElement("input");
        writer.write("\n");
        super.encodeEnd(context, component);
    }*/
}