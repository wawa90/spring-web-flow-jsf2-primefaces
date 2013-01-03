package org.springframework.samples.webflow.ajax;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.component.html.HtmlInputHidden;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.PhaseId;

//import org.apache.log4j.Logger;
import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.commandbutton.CommandButton;
import org.primefaces.component.commandlink.CommandLink;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.panel.Panel;
import org.primefaces.component.password.Password;
import org.primefaces.util.ComponentUtils;

public class RandomizeBean implements Serializable {

  private static final long serialVersionUID = 1L;
  //private static final Logger log = Logger.getLogger( RandomizeBean.class );

  private static final RandomRenderer renderer = new RandomRenderer(); 
  
	public void redraw( ActionEvent event ) {
	  Object oSource = event.getSource();
	  UIComponent uiComponent = event.getComponent();
	  PhaseId phaseId = event.getPhaseId();
	  Panel panel = ( Panel ) ComponentUtils.findComponent( FacesContext.getCurrentInstance().getViewRoot(), "panel" );
	  
    //log.info( String.format( "Event 'doRandomize' fired: src=%s, component=%s, phase=%s", event.getSource(), event.getComponent(), event.getPhaseId() ) );

    panel.getChildren().clear();
    
    int components = (int)( Math.random() * 200d );
    //log.info( String.format( "Adding %d components to interface.", components ) );
    for ( int i = 0; i < components ; i++ ) {
      panel.getChildren().add( renderer.createComponent() );
    }
	}
	
  private static class RandomRenderer {
    private enum Choice { InputTextarea, InputText, InputSecret, InputHidden, CommandButton, CommandLink, Calendar, Label };
    
    public UIComponent createComponent() {
      UIComponent component = null;
      Choice chosen = Choice.values()[(int)( Math.random() * ( (double) Choice.values().length ) )];
      switch( chosen ) {
        case InputTextarea:
          component = new InputTextarea();
          ( ( InputTextarea ) component ).setValue( "The earliest known appearance of the phrase is from The Michigan School Moderator, a journal that provided teachers with education-related news and suggestions for lessons.[1] In an article titled \"Interesting Notes\" in the March 14, 1885 issue, the phrase is given as a suggestion for writing practice: \"The following sentence makes a good copy for practice, as it contains every letter of the alphabet: 'A quick brown fox jumps over the lazy dog.'\"[2] Note that the phrase in this case begins with the word \"A\" rather than \"The\". Several other early sources also use this variation." );
          ( ( InputTextarea ) component ).setCols( 80 );
          ( ( InputTextarea ) component ).setRows( 8 );
          break;
          
        case InputText:
          component = new InputText();
          ( ( InputText ) component ).setMaxlength( 80 );
          ( ( InputText ) component ).setValue( "The quick brown fox jumps over the lazy dog." );
          break;
          
        case InputSecret:
          component = new Password();
          ( ( Password ) component ).setValue( "notverysecret" );
          break;
          
        case InputHidden:
          component = new HtmlInputHidden();
          ( ( HtmlInputHidden ) component ).setValue( "This is the hidden easter egg. It's not the best!" );
          break;
          
        case CommandButton:
          component = new CommandButton();
          ( ( CommandButton ) component ).setValue( "Press to destroy the Death Star." );
          ( ( CommandButton ) component ).setOnclick( "alert( 'Pop!' );" );
          break;
          
        case CommandLink:
          component = new CommandLink();
          ( ( CommandLink ) component ).setValue( "Don't click here." );
          ( ( CommandLink ) component ).setTitle( "I can see you're tempted." );
          ( ( CommandLink ) component ).setOnclick( "alert( 'You just couldn\\'t resist, could you?' );" );
          break;
          
        case Calendar: 
          component = new Calendar();
          break;
        
        case Label:
        default: 
          component = new HtmlOutputText();
          ( ( HtmlOutputText ) component ).setValue( "This label is designer." );
      }
      component.setId( FacesContext.getCurrentInstance().getViewRoot().createUniqueId() );
      return component;
    }
  }
}
