package connection;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import com.dto.LoginDTO;

public class ADAuthenticator {  
	
	public DirContext ctx;
	public Attribute memberOf;
	public LoginDTO loginDTO;
	
	public boolean login(String username, String password) {
		
		Properties env = new Properties();

		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://169.254.21.131:389");
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		
		try {
			ctx = new InitialDirContext(env);
			
			SearchControls searchCtrls = new SearchControls();
			searchCtrls.setSearchScope(SearchControls.SUBTREE_SCOPE);
			String ldap_search_context = "CN=Users,DC=createCode,DC=com,DC=uy";
			
			@SuppressWarnings("rawtypes")
			NamingEnumeration answer = null;
			answer =ctx.search(ldap_search_context, "sAMAccountName = " + username, searchCtrls);
			
			while(answer.hasMore()){
				SearchResult sr = (SearchResult) answer.next();
				Attributes attributes = sr.getAttributes();
				memberOf = attributes.get(ldap_search_context);
				
				if (memberOf !=null) {
					
					if(memberOf.get().equals("TRUE")) {
						return true;
					}
				}
			}
		}catch (Exception e) {
			 e.printStackTrace();
			 System.out.println("error");
			 }
			 return false;
	}
	
}