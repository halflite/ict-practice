package com.example.todo.app.form;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.fail;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = AppApplication.class)
public class ArticleFormTest {

    private Validator validator;
    
    @Before
    public void setUp() throws Exception {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    @Test
    public void testGetName() {
        ArticleForm form = new ArticleForm();
        form.setName("1234");
        form.setDescription("1234");
        Set<ConstraintViolation<ArticleForm>> validate = validator.validate(form);
        
        assertThat(validate).isEmpty();
    }

    @Test
    public void testGetNameNameMaxOver() {
        ArticleForm form = new ArticleForm();
        form.setName(null);
        form.setDescription("1234");
        Set<ConstraintViolation<ArticleForm>> validate = validator.validate(form);
        
        ConstraintViolation<ArticleForm> constraintViolation = validate.stream().findFirst().get();
        String message = constraintViolation.getMessage();
        assertThat(message).isNotEmpty();
    }

    @Test
    public void testGetDescription() {
        fail("Not yet implemented");
    }

}
