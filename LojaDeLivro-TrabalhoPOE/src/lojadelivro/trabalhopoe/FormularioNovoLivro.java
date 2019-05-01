package lojadelivro.trabalhopoe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.TitledBorder;

public class FormularioNovoLivro extends JFrame
{
     private JTextField campoTitulo = new JTextField(30);
    private JTextField campoAutor = new JTextField(30);
    private JTextField campoPreco = new JTextField(30);
    
    private JComboBox comboEdicao = new JComboBox();
    
    private JComboBox comboTema = new JComboBox();
    
    private ButtonGroup grupoTipo = new ButtonGroup();
    
    private JRadioButton radioTipo1 = new JRadioButton("Capa Dura");
    private JRadioButton radioTipo2 = new JRadioButton("Edição Econômica");
    private JRadioButton radioTipo3 = new JRadioButton("Normal");
    
    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoVoltar = new JButton("Voltar");
    
    private JTextArea campoSinopse = new JTextArea();
    
    private String auxRadio = "";
    //classe interna que trata o evento para voltar a tela principal
    private class Voltar implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            new Principal();
            dispose();
        }
        
    }
    
    private class RadioListener implements ItemListener
    {

        @Override
        public void itemStateChanged(ItemEvent e) {
            
            if(radioTipo1.isSelected())
            {
                auxRadio = "Capa Dura";
            }
            else if(radioTipo2.isSelected())
            {
                auxRadio = "Edição Econômica";
            }
            else if(radioTipo3.isSelected())
            {
                auxRadio = "Normal";
            }
            
            
        }
        
    }
    
    //método que limpa os campos do formulário 
    public void limparCampos()
    {
        campoTitulo.setText("");
        campoAutor.setText("");
        campoPreco.setText("");
        comboEdicao.setSelectedIndex(0);
        comboTema.setSelectedIndex(0);
        grupoTipo.clearSelection();
        campoSinopse.setText("");
    }
    
    //classe interna para tratar evento do botao abrir formulario
    private class EventoBotaoSalvar implements ActionListener
    {
         @Override
         public void actionPerformed(ActionEvent e) {
             String auxCampoTitulo = campoTitulo.getText();
             String auxCampoAutor = campoAutor.getText();
             String auxCampoPreco = "R$ "+campoPreco.getText();
             String auxComboEdicao = 
                     (String)comboEdicao.getSelectedItem();
             String auxComboTema = (String)comboTema.getSelectedItem();
             String auxCampoSinopse = campoSinopse.getText();

             ArrayList livro = new ArrayList();
             livro.add(auxCampoTitulo);
             livro.add(auxCampoAutor);
             livro.add(auxCampoPreco);
             livro.add(auxComboEdicao);
             livro.add(auxComboTema);
             livro.add(auxCampoSinopse);
             livro.add(auxRadio);
             
             //trantando o campo para verificar se foi inserido apenas números
            String mostraTexto = "";
            Double auxCodigo = 0.0;//Tipo primitivo int para usar metodos da classe INTEGER
            boolean verificacao;
            try
            {
                auxCodigo = Double.parseDouble(campoPreco.getText());//Converte texto em numero
                verificacao = true;
            }
            //exceção que trata se um campo contém apenas números
            catch(NumberFormatException numero)
            {
              
                mostraTexto += "Insira apenas números no Preço. \n";
                //limpando o conteúdo do campo de texto
                campoPreco.setText("");//Para inserir informação no campo de texto usa setText
                verificacao = false;
            }
             
             if(verificacao == false)
             {
                //mostrando o texto montado ao usuário
                JOptionPane.showMessageDialog(null, mostraTexto);
             }else
             {
                AcessoArquivoLivro acessoArquivo = new AcessoArquivoLivro();
                boolean status = acessoArquivo.escreverArquivo("arquivoLivros.txt", livro);

                if(status)
                {
                    JOptionPane.showMessageDialog(null, "Novo cadastro de Livro salvo com sucesso!", 
                            "Mensagem de Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar novo Livro.", "Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
                }
             }

         }
    }
    
    private class EventoConsultarDadosLivro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AcessoArquivoLivro acessoArquivo = new AcessoArquivoLivro();
            ArrayList<String> dadosLivros = acessoArquivo.lerArquivo("arquivoLivros.txt");
            //=************** CHAMANDO O FRAME DE TABELA ***************==//
            new TabelaDadosConsultarLivro(dadosLivros);
        }
    }
    
    private class EventoDeletarLivro implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AcessoArquivoLivro acessoArquivo = new AcessoArquivoLivro();
            ArrayList<String> dadosLivros = acessoArquivo.lerArquivo("arquivoLivros.txt");
            //=************** CHAMANDO O FRAME DE TABELA ***************==//
            new TabelaDadosDeletarLivro(dadosLivros);
        }
    }
    
    private class NovoFornecedor implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            new FormularioNovoFornecedor().setVisible(true);
            dispose();
        }
    }
    
    private class EventoConsultarDadosFornecedor implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AcessoArquivoFornecedor acessoArquivo = new AcessoArquivoFornecedor();
            ArrayList<String> dadosFornecedores = acessoArquivo.lerArquivo("arquivoFornecedores.txt");
            //=************** CHAMANDO O FRAME DE TABELA ***************==//
            new TabelaDadosConsultarFornecedor(dadosFornecedores);
        }
    }
    
    private class EventoDeletarFornecedor implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent e) 
        {
            AcessoArquivoFornecedor acessoArquivo = new AcessoArquivoFornecedor();
            ArrayList<String> dadosFornecedor = acessoArquivo.lerArquivo("arquivoFornecedores.txt");
            //=************** CHAMANDO O FRAME DE TABELA ***************==//
            new TabelaDadosDeletarFornecedor(dadosFornecedor);
        }
    }
    
    public FormularioNovoLivro()
    {
        super("Formulário livros");
        setSize(420,500);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        Font fonte = new Font("serif", Font.PLAIN, 18);
        
        //add Menu acima
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu menuLivro = new JMenu("Livros");
        JMenu menuFornecedor = new JMenu("Fornecedores");
        
        menuBar.add(menuLivro);
        menuBar.add(menuFornecedor);
        
        JMenuItem itemConsultarLivro = new JMenuItem("Consultar");
        JMenuItem itemDeletarLivro = new JMenuItem("Deletar");
        
        menuLivro.add(itemConsultarLivro);
        menuLivro.add(itemDeletarLivro);
        
        JMenuItem itemNovoFornecedor = new JMenuItem("Novo");
        JMenuItem itemConsultarFornecedor = new JMenuItem("Consultar");
        JMenuItem itemDeletarFornecedor = new JMenuItem("Deletar");
        
        menuFornecedor.add(itemNovoFornecedor);
        menuFornecedor.add(itemConsultarFornecedor);
        menuFornecedor.add(itemDeletarFornecedor);
        
        comboEdicao.setPreferredSize(new Dimension(152, 25));
        comboEdicao.addItem("");
        comboEdicao.addItem("1°");
        comboEdicao.addItem("2°");
        comboEdicao.addItem("3°");
        comboEdicao.addItem("4°");
        comboEdicao.addItem("5°");
        comboEdicao.addItem("6°");
        comboEdicao.addItem("7°");
        comboEdicao.addItem("8°");
        comboEdicao.addItem("9°");
        comboEdicao.addItem("10°");
        
        comboTema.addItem("");
        comboTema.addItem("Amor");
        comboTema.addItem("Amor e Guerra");
        comboTema.addItem("Amor e Mistério");
        comboTema.addItem("Época");
        comboTema.addItem("Ficção");
        comboTema.addItem("Ficção Cientifíca");
        comboTema.addItem("Literatura Estrangeira");
        comboTema.addItem("Mistério");
        comboTema.addItem("Mistério e Terror");
        comboTema.addItem("Romance");
        comboTema.addItem("Terror");
        
        radioTipo1.setFont(fonte);
        radioTipo2.setFont(fonte);
        radioTipo3.setFont(fonte);
        
        grupoTipo.add(radioTipo1);
        grupoTipo.add(radioTipo2);
        grupoTipo.add(radioTipo3);
        
        Container c = getContentPane();
        c.setLayout(new GridLayout(1,1));
        
        JPanel painelPrincipal = new JPanel(new GridLayout(9,1));
        painelPrincipal.setBorder(new TitledBorder
        (BorderFactory.createLineBorder(Color.WHITE), "Cadastro de livros"));
         painelPrincipal.setBackground(Color.BLACK);
         
         JPanel linha1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
         linha1.setBackground(Color.BLACK);
         JLabel jTitulo = new JLabel("Titulo: ");
         jTitulo.setForeground(Color.RED);
         linha1.add(jTitulo);
         linha1.add(campoTitulo);
         
         JPanel linha2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
         linha2.setBackground(Color.BLACK);
         JLabel jAutor = new JLabel("Autor: ");
         jAutor.setForeground(Color.RED);
         linha2.add(jAutor);
         linha2.add(campoAutor);
         
         JPanel linha3 = new JPanel(new FlowLayout(FlowLayout.LEADING));
         linha3.setBackground(Color.BLACK);
         JLabel jPreco = new JLabel("Preço: ");
         jPreco.setForeground(Color.RED);
         linha3.add(jPreco);
         linha3.add(campoPreco);
         
         JPanel linha4 = new JPanel(new FlowLayout(FlowLayout.LEADING));
         linha4.setBackground(Color.BLACK);
         JLabel jEdicao = new JLabel("Edição: ");
         jEdicao.setForeground(Color.RED);
         linha4.add(jEdicao);
         linha4.add(comboEdicao);
         
         JPanel linha5 = new JPanel(new FlowLayout(FlowLayout.LEADING));
         linha5.setBackground(Color.BLACK);
         JLabel jTema = new JLabel("Tema:   ");
         jTema.setForeground(Color.RED);
         linha5.add(jTema);
         linha5.add(comboTema);
         
         //criando Texto de Área
        campoSinopse = new JTextArea(100, 5);
        JScrollPane painelRolagem = new JScrollPane(campoSinopse);
         
         JPanel linha6 = new JPanel(new GridLayout(1, 1));
         linha6.setBackground(Color.BLACK);
         JLabel jSinopse = new JLabel("Sinopse:   ");
         jSinopse.setForeground(Color.RED);
         linha6.add(jSinopse);
         linha6.add(painelRolagem);
         
         JPanel linha7 = new JPanel(new FlowLayout(FlowLayout.LEADING));
         linha7.setBackground(Color.BLACK);
         JLabel jTipo = new JLabel("Tipo: ");
         jTipo.setForeground(Color.RED);
         linha7.add(jTipo);
         
         radioTipo1.setForeground(Color.RED);
         radioTipo2.setForeground(Color.RED);
         radioTipo3.setForeground(Color.RED);
         
         radioTipo1.setBackground(Color.BLACK);
         radioTipo2.setBackground(Color.BLACK);
         radioTipo3.setBackground(Color.BLACK);
         
         linha7.add(radioTipo1);
         linha7.add(radioTipo2);
         linha7.add(radioTipo3);
         
         JPanel linha8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
         linha8.setBackground(Color.BLACK);
         
         botaoSalvar.setBackground(Color.RED);
         botaoVoltar.setBackground(Color.RED);
         
         botaoSalvar.setPreferredSize(new Dimension(70, 40));
         botaoVoltar.setPreferredSize(new Dimension(70, 40));
         
         Container cAbaixoBtE = new Container();
        cAbaixoBtE.setLayout(new FlowLayout(FlowLayout.RIGHT));
        cAbaixoBtE.add(botaoSalvar);
        
        Container cAbaixoBtD = new Container();
        cAbaixoBtD.setLayout(new FlowLayout(FlowLayout.RIGHT));
        cAbaixoBtD.add(botaoVoltar);
         
         linha8.add(cAbaixoBtE);
         linha8.add(cAbaixoBtD);
         
         painelPrincipal.add(linha1);
         painelPrincipal.add(linha2);
         painelPrincipal.add(linha3);
         painelPrincipal.add(linha4);
         painelPrincipal.add(linha5);
         painelPrincipal.add(linha6);
         painelPrincipal.add(linha7);
         painelPrincipal.add(linha8);
         
         c.add(painelPrincipal);
         
         botaoVoltar.addActionListener(new Voltar());
         botaoSalvar.addActionListener(new EventoBotaoSalvar());
         radioTipo1.addItemListener(new RadioListener());
         radioTipo2.addItemListener(new RadioListener());
         radioTipo3.addItemListener(new RadioListener());
         
        itemConsultarLivro.addActionListener(new EventoConsultarDadosLivro());
        itemDeletarLivro.addActionListener(new EventoDeletarLivro());
        
        itemNovoFornecedor.addActionListener(new NovoFornecedor());
        itemConsultarFornecedor.addActionListener(new EventoConsultarDadosFornecedor());
        itemDeletarFornecedor.addActionListener(new EventoDeletarFornecedor());
         
    }//Fim do construtor
}
