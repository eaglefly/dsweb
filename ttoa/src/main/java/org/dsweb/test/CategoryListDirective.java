package org.dsweb.test;

import static freemarker.template.ObjectWrapper.DEFAULT_WRAPPER;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.dsweb.test.model.Meinv;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

public class CategoryListDirective implements TemplateDirectiveModel {

    
    @SuppressWarnings({ "rawtypes", "deprecation" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body) throws TemplateException, IOException {

        List<Meinv> list = new ArrayList<Meinv>();
        if (params.containsKey("parent_id") && params.get("parent_id") != null) {
            list = Meinv.dao.findAll();
        }
        
        env.setVariable("list", DEFAULT_WRAPPER.wrap(list));
        body.render(env.getOut());
    }

}
