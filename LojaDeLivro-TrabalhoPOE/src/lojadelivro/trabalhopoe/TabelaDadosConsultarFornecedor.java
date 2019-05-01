package lojadelivro.trabalhopoe;

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class TabelaDadosConsultarFornecedor extends JFrame
{
    private JTable tabela;

    public TabelaDadosConsultarFornecedor(ArrayList<String> fornecedores)
    {
        super("Formulário Fornecedor");
        setSize(900,400);
        setVisible(true);
        setLocationRelativeTo(null);
        //fechando este JFrame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
        //array com os nomes das colunas - header
        String[] nomeColunas = {"Denominação Social", "Endereço", "Numero", "Bairro", "Cidade","UF", "Cep", "Telefone"};
        
        //para inserir dados em uma JTable os mesmos devem estar contidos em um array de Objects
        //como sabemos que eles sempre serão divididos em 3 colunas (Nome, Disciplinas e Mensagem)
        //basta saber a quantidade de linhas que no momento tem no arquivo
        //para isso usa-se o método size() de ArrayList
        Object dados[][] = new Object[(fornecedores.size())+50][8];
        
        //inicializando contador auxiliar para auxiliar na inserção do array de Objects
        //ele fixará as linhas para que o for interno rode as colunas conforme a linha resgatada pelo split(); 
        int j = 0;
        
        for(String aux : fornecedores)
        {
            //separando a String (linha) inteira do arquivo pelo caracter ";"
            //Ou seja, onde houver um ";" a(s) palavra(s) sucessoras ficarão na posição posterior do array
            String[] auxLinhas = aux.split(";");
            for(int i = 0; i < auxLinhas.length; i++)
            {
                //inserindo cada linha separada (";") em sua coluna correspondente na tabela
                dados[j][i] = auxLinhas[i];
            }
            j++;           
        }
        //criando um modelo simples para a Tabela, passando os dados que serão inseridos 
        //e a identificação de cada coluna
        DefaultTableModel modelo = 
                new DefaultTableModel(dados, nomeColunas);
        //criando a table
        tabela = new JTable(modelo);
        //usando a tabela como Container do Frame para utilizar o JScrollBar
        getContentPane().add(new JScrollPane(tabela));
        //deixando as linhas da tabel não editáveis
        tabela.setEnabled(false);
    }
}
