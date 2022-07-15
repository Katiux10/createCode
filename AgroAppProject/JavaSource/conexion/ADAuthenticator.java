package conexion;

import java.util.Hashtable;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import static javax.naming.directory.SearchControls.SUBTREE_SCOPE;
import javax.naming.ldap.LdapContext;
import javax.naming.ldap.InitialLdapContext;
 
//Imports for changing password
import javax.naming.directory.ModificationItem;
import javax.naming.directory.BasicAttribute;
import javax.naming.ldap.StartTlsResponse;
import javax.naming.ldap.StartTlsRequest;
import javax.net.ssl.*;

import com.dto.LoginDTO;
import com.exception.ServiciosException;

public class ADAuthenticator {  
	
	public DirContext ctx;
	public Attribute memberOf;
	public LoginDTO loginDTO;
	
	public ADAuthenticator(){}
	
	public boolean login(String username, String password) {
		
		Properties env = new Properties();
		if (username.contains("@")){
            username = username.substring(0, username.indexOf("@"));
        }
		env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://192.168.0.195:389");
		env.put(Context.SECURITY_PRINCIPAL, username);
		env.put(Context.SECURITY_CREDENTIALS, password);
		
		try {
			ctx = new InitialDirContext(env);
			System.out.println("Esta es mi conexion: " + env);
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



	 