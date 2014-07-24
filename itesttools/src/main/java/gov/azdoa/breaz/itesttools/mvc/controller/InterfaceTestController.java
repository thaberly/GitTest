package gov.azdoa.breaz.itesttools.mvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/* Only one controller needed for this app for
 * redirecting all requests
 */
public class InterfaceTestController {
	
	
	@RequestMapping(value="/xmlFieldComparisonForm", method=RequestMethod.GET)
	protected String displayFieldComparisonForm(HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		return new String("fieldComparisonForm");
	}
	
	
	/* Compare fields in XML with that in the spec (spreadsheet)
	 */
	@RequestMapping(value="/xmlFieldComparison", method=RequestMethod.GET)
	protected String compareXMLfieldsWithSpec(HttpServletRequest request,
		HttpServletResponse response) throws Exception {

		return new String("");
	}
	
	
	
	
	
}
