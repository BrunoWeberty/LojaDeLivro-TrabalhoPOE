package lojadelivro.trabalhopoe;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.table.DefaultTableModel;

public class TabelaDadosDeletarLivro extends JFrame
{
    private JTable tabela;

    public TabelaDadosDeletarLivro(ArrayList<String> livros) 
    {
        super("Formulário Livro");
        setSize(900,400);
        setVisible(true);
        setLocationRelativeTo(null);
        //fechando este JFrame
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    
        //array com os nomes das colunas - header
        //adicionando um campo que servirá como id e contador de linha
        String[] nomeColunas = {"#", "Titulo", "Autor", "Preço", "Edicão", "Tema","Sinopse", "Tipo"};
        
        //para inserir dados em uma JTable os mesmos devem estar contidos em um array de Objects
        //como sabemos que eles sempre serão divididos em 4 colunas (auxiliarID, Nome, Disciplinas e Mensagem)
        //basta saber a quantidade de linhas que no momento tem no arquivo
        //para isso usa-se o método size() de ArrayList
        Object dados[][] = new Object[(livros.size())+50][8];
        
        //inicializando contador auxiliar para auxiliar na inserção do array de Objects
        //ele fixará as linhas para que o for interno rode as colunas conforme a linha resgatada pelo split(); 
        int j = 0;
        //contador que simula o ID
        int cont = 1;
        
        for(String aux : livros)
        {
            //separando a String (linha) inteira do arquivo pelo caracter ";"
            //Ou seja, onde houver um ";" a(s) palavra(s) sucessoras ficarão na posição posterir do array
            String[] auxLinhas = aux.split(";");
            for(int i = 0; i < auxLinhas.length; i++)
            {
                //se for a primeira coluna, insira o número sequencial que simula um id
                if(i == 0)
                {
                    dados[j][i] = cont;
                }
                
                //inserindo cada linha separada (";") em sua coluna correspondente na tabela
                //uma posição a frente por causa da inserção do aux ID
                dados[j][i+1] = auxLinhas[i];
                
            }
            cont++;
            j++;           
        }
        //criando um modelo simples para a Tabela, passando os dados que serão inseridos 
        //e a identificação de cada coluna
        DefaultTableModel modelo = new DefaultTableModel(dados, nomeColunas);
        //criando a table
        tabela = new JTable(modelo);
        //usando a tabela como Conatainer do Frame para utilizar o JScrollBar
        getContentPane().add(new JScrollPane(tabela));
        
        //adicionando evento de mouse na tabela
        tabela.addMouseListener(new EventoLinhaTabela());
    }
    
    private class EventoLinhaTabela implements MouseListener
    {

        @Override
        public void mouseClicked(MouseEvent e) 
        {
            //se o usuário clicar apenas 1 vez sobre a linha
            if(e.getClickCount() == 1)
            {
                //capturando a linha selecionada
                int linhaClicada = tabela.getSelectedRow();
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja deletar essa informação?");
                if(resposta == 0)
                {
                   //capturando o valor na coluna que simula o id
                   int identificadorLinha = Integer.parseInt(tabela.getValueAt(linhaClicada, 0).toString());
                   AcessoArquivoLivro acessoArquivo = new AcessoArquivoLivro();
                   boolean status = acessoArquivo.deletarLinhaArquivo(identificadorLinha, "arquivoLivros.txt");
                   if(status)
                   {
                       JOptionPane.showMessageDialog(null, "Registro deletado com sucesso!!");
                       dispose(); 
                       
                   }
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }
        
    }
}
