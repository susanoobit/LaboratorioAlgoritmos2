package TP2;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Scanner;
import java.util.function.Predicate;

public class BankService {
	public static List<Account> accounts = new List<Account>();
	private static Table dbTableInfo;
	private static boolean hasTableToBeBuild = false;
	
	public static void main(String[] args) throws Exception {
		if (args.length == 0 || args[0].length() == 0) {
			System.out.println("Uso: java BankService <nome_do_arquivo>");
			System.exit(0);
		}
		
    	startInteface(args[0]);
	}
	
	public static void readDataBase(String filePath) throws NumberFormatException, IOException, Exception {
		FileReader fr = new FileReader(filePath);
    	if (!StandardCharsets.US_ASCII.isSupported(fr.getEncoding())) {
    		System.out.println("O arquivo deve estar codificado em ASCII. Codificação atual: " + fr.getEncoding());
			System.exit(0);
    	}
    	
		BufferedReader in = new BufferedReader(fr);
        
        while (true) {
        	String clientName = in.readLine(),
        		accountCodeS = "",
        		accountBalance = "";
        	
        	if (accounts.getLength() > 0 && clientName == null) break;
        	
        	try {
        		clientName = clientName.trim();
                accountCodeS = in.readLine().trim();
                accountBalance = in.readLine().trim().replace(".", "").replaceAll(",", ".");
        	}
        	catch (NullPointerException e) {
        		System.out.println("Estrutura de dados incorreta.");
        		in.close();
        		System.exit(0);
        	}
        	
        	if (accountCodeS.length() != Account.MAX_CODE_LENGTH) {
        		System.out.println("Estrutura de dados incorreta. O código da conta do(a) cliente " + clientName + " deve ter " + Account.MAX_CODE_LENGTH + " caracteres.");
        		in.close();
        		System.exit(0);
        	}
        		
        	int[] accountCode = new int[Account.MAX_CODE_LENGTH];
        	for (int i = 0; i < Account.MAX_CODE_LENGTH; accountCode[i] = Integer.parseUnsignedInt(String.valueOf(accountCodeS.charAt(i))), i++);
        	
        	accounts.push(new Account(clientName, accountCode, Float.parseFloat(accountBalance)));
        }
        
        in.close();
	}
	
	private static void overwriteDataBase(String filePath) throws Exception {
		BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
		
		Account[] accountsA = new Account[accounts.getLength()];
		accounts.toArray(accountsA);
		for (int i = 0; i < accountsA.length; i++) {
			out.write(accountsA[i].getClient().getName());
			out.newLine();
			out.write(accountsA[i].getStringCode());
			out.newLine();
			out.write(String.valueOf(accountsA[i].getBalance()));
			if (i != accountsA.length - 1) out.newLine();
			out.flush();
		}
		
		accountsA = null;
		out.close();
	}
	
	private static void startInteface(String dbPath) throws Exception {
		printGreetings();
		
		System.out.print("Lendo o banco de dados... ");
		readDataBase(dbPath);
		System.out.print("Concluido.");
		System.out.println("\n");
		
		Scanner reader = new Scanner(System.in);
		while (true) {
			printMainMenu();
			int mainMenuOption = reader.nextInt();
			System.out.println("\n");
			switch (mainMenuOption) {
				case 1:
					startSearchInterface(reader);
					break;
				case 2:
					startBalanceUpdateInterface(reader, -1);
					break;
				case 3:
					startBalanceUpdateInterface(reader, 1);
					break;
				case 4:
					printAccounts();
					break;
				case 5:
					startSortInterface(reader);
					break;
				case 0:
					System.out.print("Salvando informacoes... ");
					overwriteDataBase(dbPath);
					System.out.print("Concluido.");
					System.out.println("\n");
					printBye();
					return;
				default:
					System.out.println("Opcao invalida.");
					System.out.println("\n");
			}
		}
	}

	private static void printGreetings() {
		System.out.println("                                . . . .\n");
		System.out.println("                                ,`,`,`,`,\n");
		System.out.println("          . . . .               `\\`\\`\\`\\;\n");
		System.out.println("          `\\`\\`\\`\\`,            ~|;!;!;\\!\n");
		System.out.println("           ~\\;\\;\\;\\|\\          (--,!!!~`!       .\n");
		System.out.println("          (--,\\\\\\===~\\         (--,|||~`!     ./\n");
		System.out.println("           (--,\\\\\\===~\\         `,-,~,=,:. _,//\n");
		System.out.println("            (--,\\\\\\==~`\\        ~-=~-.---|\\;/J,\n");
		System.out.println("             (--,\\\\\\((```==.    ~'`~/       a |\n");
		System.out.println("               (-,.\\\\('('(`\\\\.  ~'=~|     \\_.  \\\n");
		System.out.println("                  (,--(,(,(,'\\\\. ~'=|       \\\\_;>\n");
		System.out.println("                    (,-( ,(,(,;\\\\ ~=/        \\\n");
		System.out.println("                    (,-/ (.(.(,;\\\\,/          )\n");
		System.out.println("                     (,--/,;,;,;,\\\\         ./------.\n");
		System.out.println("                       (==,-;-'`;'         /_,----`. \\\n");
		System.out.println("               ,.--_,__.-'                    `--.  ` \\\n");
		System.out.println("              (='~-_,--/        ,       ,!,___--. \\  \\_)\n");
		System.out.println("             (-/~(     |         \\   ,_-         | ) /_|\n");
		System.out.println("             (~/((\\    )\\._,      |-'         _,/ /\n");
		System.out.println("              \\\\))))  /   ./~.    |           \\_\\;\n");
		System.out.println("           ,__/////  /   /    )  /\n");
		System.out.println("            '===~'   |  |    (, <.\n");
		System.out.println("                     / /       \\. \\\n");
		System.out.println("                   _/ /          \\_\\\n");
		System.out.println("                  /_!/            >_\\\n");
		System.out.println("\n");
		System.out.println("=========   Bem vindo ao Banco Unicornio!   =========");
		System.out.println("\n");
	}
	
	private static void printMainMenu() {
		System.out.println("Selecione uma das opcoes abaixo:");
		System.out.println("1. Buscar conta");
		System.out.println("2. Saque");
		System.out.println("3. Deposito");
		System.out.println("4. Mostrar contas");
		System.out.println("5. Ordenar contas");
		System.out.println("0. Sair");
	}
	
	private static void startSearchInterface(Scanner reader) throws Exception {
		printSearchAccountMenu();
		int searchTypeOption = reader.nextInt();
		switch (searchTypeOption) {
			case 1:
				startAccountSearchByClientNameMenu(reader);
				break;
			case 2:
				startAccountSearchByCodeMenu(reader);
				break;
			case 0:
				System.out.println("\n");
				return;
			default:
				System.out.println("Opcao invalida.");
				System.out.println("\n");
				return;
		}
	}
	
	private static void printSearchAccountMenu() {
		System.out.println("Por meio de qual informação gostaria de buscar uma conta?");
		System.out.println("1. Nome do cliente");
		System.out.println("2. Código da conta");
		System.out.println("0. Voltar");
	}
	
	private static void startAccountSearchByMenu(Scanner reader, Predicate<Account> tester, CheckedConsumer<Scanner> function) throws Exception {
		List<Account> results = accounts.filter(tester);
		int length = results.getLength();
		if (length == 0) {
			printSearchFail();
			int option = reader.nextInt();
			switch (option) {
				case 1:
					function.accept(reader);
					break;
				case 2:
					startSearchInterface(reader);
					break;
				case 3:
				default:;
			}
		}
		else if (length == 1)
			System.out.println("Conta encontrada: " + results.elementAt(0).toString() + ".");
		else {
			System.out.println("Foram encontradas " + length + " que atendem ao criterio de busca: ");
			for (int i = 0; i < length; i++)
				System.out.println(results.elementAt(i).toString());
		}
		System.out.println("\n");
		results = null;
	}
	
	private static void startAccountSearchByClientNameMenu(Scanner reader) throws Exception {
		System.out.println("Digite o nome: ");
		String nameSnippet = reader.next().trim();
		startAccountSearchByMenu(reader,
				p -> p.getClient().getName().toLowerCase().contains(nameSnippet.toLowerCase()),
				BankService::startAccountSearchByClientNameMenu);
	}
	
	private static void startAccountSearchByCodeMenu(Scanner reader) throws Exception {
		System.out.println("Digite o código: ");
		String code = reader.next().trim();
		if (code.length() != Account.MAX_CODE_LENGTH) {
			System.out.println("O código deve ter " + Account.MAX_CODE_LENGTH + " caracteres.");
			startAccountSearchByCodeMenu(reader);
		}
		else { 
			startAccountSearchByMenu(reader,
					p -> p.getStringCode().equals(code),
					BankService::startAccountSearchByCodeMenu);
		}
	}
	
	private static void printSearchFail() {
		System.out.println("Nao foi encontrada nenhuma correspondencia. Deseja tentar novamente?");
		System.out.println("1. Sim");
		System.out.println("2. Nao, e voltar ao menu de pesquisa");
		System.out.println("3. Nao, e voltar ao menu principal");
	}
	
	private static void startBalanceUpdateInterface(Scanner reader, int i) throws Exception {
		while (true) {
			System.out.println("Digite o codigo da conta ou 'x' para voltar ao menu inicial:");
			String accountCodeS = reader.next().trim();
			
			if (accountCodeS.toLowerCase().equals("x")) {
				System.out.println("\n");
				return;
			}
			
			Exception ex = new Exception();
			try { Integer.parseUnsignedInt(accountCodeS); ex = null; } catch(Exception e) { ex = e; }
			
			if (accountCodeS.length() != Account.MAX_CODE_LENGTH || ex != null) {
				System.out.println("Digite um codigo numerico de " + Account.MAX_CODE_LENGTH + " digitos, por favor.");
				ex = null;
				continue;
			}
			
			List<Account> searchResult = accounts.filter(a -> a.getStringCode().equals(accountCodeS));
			if (searchResult.getLength() == 0) {
				System.out.println("Conta nao encontrada. Digite um codigo de conta existente, por favor.");
				searchResult = new List<Account>();
				continue;
			}
			
			Account foundAccount = searchResult.elementAt(0);
			System.out.println("Conta encontrada. Saldo atual: " + foundAccount.getCurrencyBalance("R$", ",") + ".");
			
			while (true) {
				System.out.println("Digite o valor ou 'x' para voltar ao menu inicial:");
				String valueS = reader.next().trim().replace(".", "").replaceAll(",", ".");
				
				if (valueS.toLowerCase().equals("x")) {
					System.out.println("\n");
					return;
				}

				ex = new Exception();
				try { Float.parseFloat(valueS); ex = null; } catch(Exception e) { ex = e; }
				
				if (valueS.length() == 0 || ex != null) {
					System.out.println("Digite um valor valido, por favor.");
					ex = null;
					continue;
				}
				
				float value = Float.parseFloat(valueS) * i;
				if (foundAccount.getBalance() + value < 0) {
					System.out.println("Conta com saldo insuficiente para esta operacao. Tente novamente.");
					continue;
				}
				
				System.out.println("Efetuando operacao...");
				foundAccount.setBalance(foundAccount.getBalance() + value);
				System.out.println("Operação efetuada. Novo saldo: " + foundAccount.getCurrencyBalance("R$", ",") + ".");
				System.out.println("\n");
				return;
			}
		}		
	}
	
	public static void printAccounts() throws Exception {
		if (dbTableInfo != null && !hasTableToBeBuild) {
			System.out.println(dbTableInfo.printToString());
			return;
		}
		
		Account[] accountsA = new Account[accounts.getLength()];
		accounts.toArray(accountsA);
		List<Row> rows = 
			Arrays.stream(accountsA)
				.reduce(new List<Row>(),
					(lr, a) -> lr.pushThen(new Row(
						(new List<TableCell>())
							.pushThen(new TableCell(a.getClient().getName()))
							.pushThen(new TableCell(a.getStringCode()))
							.pushThen(new TableCell(String.valueOf(a.getBalance())))
						)
					),
					(lr1, lr2) -> {
						try {
							return lr1.merge(lr2);
						} catch (Exception e) {
							e.printStackTrace();
						}
						return lr1;
					});
		
		Row header = new Row(
			(new List<TableCell>())
				.pushThen(new TableCell("Nome do Cliente"))
				.pushThen(new TableCell("Codigo da conta"))
				.pushThen(new TableCell("Saldo"))
		);
		
		dbTableInfo = new Table(3, header, rows);
		System.out.println(dbTableInfo.printToString());
		System.out.println("\n");
		hasTableToBeBuild = false;
	}
	
	private static void startSortInterface(Scanner reader) throws Exception {
		printSortAccountsMenu();
		int searchTypeOption = reader.nextInt();
		System.out.println("Ordenando...");
		switch (searchTypeOption) {
			case 1:
				accounts.sortBy(Account::compareByClient);
				break;
			case 2:
				accounts.sortBy(Account::compareByCode);
				break;
			case 3:
				accounts.sortBy(Account::compareByBalance);
				break;
			case 0:
				System.out.println("\n");
				return;
			default:
				System.out.println("Opcao invalida.");
				System.out.println("\n");
				return;
		}
		hasTableToBeBuild = true;
		System.out.println("Contas ordenadas. Gostaria de ver o resultados?");
		System.out.println("1. Sim");
		System.out.println("2. Não");
		int showResultsOption = reader.nextInt();
		System.out.println("\n");
		if (showResultsOption == 1) printAccounts();
	}
	
	private static void printSortAccountsMenu() {
		System.out.println("Qual critério gostaria de usar para ordenar as contas?");
		System.out.println("1. Nome do cliente");
		System.out.println("2. Código da conta");
		System.out.println("3. Saldo da conta");
		System.out.println("0. Voltar");
	}
	
	private static void printBye() {
		System.out.println("    \\.\n");
		System.out.println("     \\'.      ;.\n");
		System.out.println("      \\ '. ,--''-.~-~-'-,\n");
		System.out.println("       \\,-' ,-.   '.~-~-~~,\n");
		System.out.println("     ,-'   (###)    \\-~'~=-.\n");
		System.out.println(" _,-'       '-'      \\=~-\"~~',\n");
		System.out.println("/o                    \\~-\"\"~=-,\n");
		System.out.println("\\__                    \\=-,~\"-~,\n");
		System.out.println("   \"\"\"===-----.         \\~=-\"~-.\n");
		System.out.println("               \\         \\*=~-\"\n");
		System.out.println("   ~BYE~        \\         \"=====----\n");
		System.out.println("                 \\\n");
		System.out.println("                  \\\n");
		System.out.println("\n");
		System.out.println("=========   Obrigado por usar o Banco Unicórnio!   =========");
	}
}
