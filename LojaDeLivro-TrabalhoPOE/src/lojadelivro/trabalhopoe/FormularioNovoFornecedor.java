package lojadelivro.trabalhopoe;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import javax.swing.border.TitledBorder;

public class FormularioNovoFornecedor extends JFrame
{
    private JTextField campoDS = new JTextField(21);
    private JTextField campoEndereco = new JTextField(17);
    private JTextField campoNumero = new JTextField(14);
    private JTextField campoBairro = new JTextField(12);
    private JTextField campoCidade = new JTextField(11);
    private JComboBox comboUf = new JComboBox();
    private JTextField campoCep = new JTextField(12);
    private JTextField campoTelefone = new JTextField(10);
    
    private JButton botaoSalvar = new JButton("Salvar");
    private JButton botaoVoltar = new JButton("Voltar");
    
    private class Voltar implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
            new Principal();
            dispose();
        }
        
    }
    
    //método que limpa os campos do formulário 
    public void limparCampos()
    {
        campoDS.setText("");
        campoEndereco.setText("");
        campoNumero.setText("");
        campoBairro.setText("");
        campoCidade.setText("");
        comboUf.setSelectedIndex(0);
        campoCep.setText("");
        campoTelefone.setText("");
    }
    
    //classe interna para tratar evento do botao abrir formulario
    private class EventoBotaoSalvar implements ActionListener
    {
         @Override
         public void actionPerformed(ActionEvent e) {
             String auxCampoDS = campoDS.getText();
             String auxCampoEndereco = campoEndereco.getText();
             String auxCampoNumero = campoNumero.getText();
             String auxCampoBairro = campoBairro.getText();
             String auxCampoCidade = campoCidade.getText();
             String auxComboUF = (String)comboUf.getSelectedItem();
             String auxCampoCep = (String)campoCep.getText();
             String auxCampoTelefone = campoTelefone.getText();

             ArrayList fornecedor = new ArrayList();
             fornecedor.add(auxCampoDS);
             fornecedor.add(auxCampoEndereco);
             fornecedor.add(auxCampoNumero);
             fornecedor.add(auxCampoBairro);
             fornecedor.add(auxCampoCidade);
             fornecedor.add(auxComboUF);
             fornecedor.add(auxCampoCep);
             fornecedor.add(auxCampoTelefone);
             
             //trantando o campo para verificar se foi inserido apenas números
            String mostraTexto = "";
            Integer auxCodigo = 0;//Tipo primitivo int para usar metodos da classe INTEGER
            boolean verificacao1;
            boolean verificacao2;
            boolean verificacao3;
            boolean verificacao4 = true;
            try
            {
                auxCodigo = Integer.parseInt(campoNumero.getText());//Converte texto em numero
                verificacao1 = true;
            }
            //exceção que trata se um campo contém apenas números
            catch(NumberFormatException numero)
            {
              
                mostraTexto += "Insira apenas números no Numero. \n";
                //limpando o conteúdo do campo de texto
                campoNumero.setText("");//Para inserir informação no campo de texto usa setText
                verificacao1 = false;
            }
            
            try
            {
                auxCodigo = Integer.parseInt(campoCep.getText());//Converte texto em numero
                verificacao2 = true;
            }
            //exceção que trata se um campo contém apenas números
            catch(NumberFormatException numero)
            {
              
                mostraTexto += "Insira apenas números no Cep. \n";
                //limpando o conteúdo do campo de texto
                campoCep.setText("");//Para inserir informação no campo de texto usa setText
                verificacao2 = false;
            }
            
            try
            {
                auxCodigo = Integer.parseInt(campoTelefone.getText());//Converte texto em numero
                verificacao3 = true;
            }
            //exceção que trata se um campo contém apenas números
            catch(NumberFormatException numero)
            {
              
                mostraTexto += "Insira apenas números no Telefone. \n";
                //limpando o conteúdo do campo de texto
                campoTelefone.setText("");//Para inserir informação no campo de texto usa setText
                verificacao3 = false;
            }
            
            if(campoNumero.equals("") && (campoCep.equals("") && campoTelefone.equals("")))
            {
                mostraTexto += "Preencha os Campus Numero, Cep e Telefone!\n";
                verificacao4 = false;
            }
            
             if(verificacao1 == false)
             {
                //mostrando o texto montado ao usuário
                JOptionPane.showMessageDialog(null, mostraTexto);
             }else if(verificacao2 == false)
             {
                JOptionPane.showMessageDialog(null, mostraTexto);
             }else if(verificacao3 == false)
             {
                JOptionPane.showMessageDialog(null, mostraTexto);
             }else if(verificacao4 == false)
             {
                JOptionPane.showMessageDialog(null, mostraTexto);
             }else
             {
                AcessoArquivoFornecedor acessoArquivo = new AcessoArquivoFornecedor();
                boolean status = acessoArquivo.escreverArquivo("arquivoFornecedores.txt", fornecedor);

                if(status)
                {
                    JOptionPane.showMessageDialog(null, "Novo cadastro de Fornecedor salvo com sucesso!", 
                            "Mensagem de Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    limparCampos();
                }
                else
                {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar novo Fornecedor.", "Mensagem de Erro", JOptionPane.ERROR_MESSAGE);
                }
             }
             

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
    
    private class NovoLivro implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            new FormularioNovoLivro().setVisible(true);
            dispose();
        }
    }
       //classe interna que trata o evento no item de menu do professor
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
    
    public FormularioNovoFornecedor()
    {
        super("Formulário fornecedores");
        setSize(445,335);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
        //add Menu acima
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        
        JMenu menuLivro = new JMenu("Livros");
        JMenu menuFornecedor = new JMenu("Fornecedores");
        
        menuBar.add(menuLivro);
        menuBar.add(menuFornecedor);
        
        JMenuItem itemNovoLivro = new JMenuItem("Novo");
        JMenuItem itemConsultarLivro = new JMenuItem("Consultar");
        JMenuItem itemDeletarLivro = new JMenuItem("Deletar");
        
        menuLivro.add(itemNovoLivro);
        menuLivro.add(itemConsultarLivro);
        menuLivro.add(itemDeletarLivro);
        
        JMenuItem itemConsultarFornecedor = new JMenuItem("Consultar");
        JMenuItem itemDeletarFornecedor = new JMenuItem("Deletar");
        
        menuFornecedor.add(itemConsultarFornecedor);
        menuFornecedor.add(itemDeletarFornecedor);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEADING));
        
        Font fonte = new Font("serif", Font.PLAIN, 18);
        
        JLabel lDS = new JLabel("Denominação Social:  ");
        lDS.setFont(fonte);
        lDS.setForeground(Color.RED);
        
        JPanel painel1 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel1.setBorder(new TitledBorder
        (BorderFactory.createLineBorder(Color.RED), "Nome"));
        painel1.setBackground(Color.BLACK);
        
        painel1.add(lDS);
        painel1.add(campoDS);
        
        JPanel painelPrincipalF = new JPanel(new GridLayout(5, 2));
        painelPrincipalF.setBorder(new TitledBorder
        (BorderFactory.createLineBorder(Color.RED), "Endereco"));
        painelPrincipalF.setBackground(Color.BLACK);
        
        JLabel lEndereco = new JLabel("Endereço: ");
        lEndereco.setFont(fonte);
        lEndereco.setForeground(Color.RED);
        
        JPanel painel2 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel2.setBackground(Color.BLACK);
        
        painel2.add(lEndereco);
        
        JPanel painel3 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel3.add(campoEndereco);
        painel3.setBackground(Color.BLACK);
        
        JLabel lNumero = new JLabel("N°: ");
        lNumero.setFont(fonte);
        lNumero.setForeground(Color.RED);
        
        JPanel painel4 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel4.setBackground(Color.BLACK);
        painel4.add(lNumero);
        painel4.add(campoNumero);
        
        JLabel lBairro = new JLabel("Bairro: ");
        lBairro.setFont(fonte);
        lBairro.setForeground(Color.RED);
        
        JPanel painel5 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel5.setBackground(Color.BLACK);
        painel5.add(lBairro);
        painel5.add(campoBairro);
        
        JLabel lCidade = new JLabel("Cidade: ");
        lCidade.setFont(fonte);
        lCidade.setForeground(Color.RED);
        
        JPanel painel6 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel6.setBackground(Color.BLACK);
        painel6.add(lCidade);
        painel6.add(campoCidade);
        
        JLabel lUF = new JLabel("UF: ");
        lUF.setFont(fonte);
        lUF.setForeground(Color.RED);
        
        comboUf.addItem("");
        comboUf.addItem("AC");
        comboUf.addItem("AL");
        comboUf.addItem("AM");
        comboUf.addItem("AP");
        comboUf.addItem("BA");
        comboUf.addItem("CE");
        comboUf.addItem("DF");
        comboUf.addItem("ES");
        comboUf.addItem("GO");
        comboUf.addItem("MA");
        comboUf.addItem("MG");
        comboUf.addItem("MS");
        comboUf.addItem("MT");
        comboUf.addItem("PA");
        comboUf.addItem("PB");
        comboUf.addItem("PE");
        comboUf.addItem("PI");
        comboUf.addItem("PR");
        comboUf.addItem("RJ");
        comboUf.addItem("RN");
        comboUf.addItem("RO");
        comboUf.addItem("RR");
        comboUf.addItem("RS");
        comboUf.addItem("SC");
        comboUf.addItem("SE");
        comboUf.addItem("SP");
        comboUf.addItem("TO");
        
        comboUf.setPreferredSize(new Dimension(155, 20));
        
        JPanel painel7 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel7.setBackground(Color.BLACK);
        painel7.add(lUF);
        painel7.add(comboUf);
        
        JLabel lCep = new JLabel("CEP: ");
        lCep.setFont(fonte);
        lCep.setForeground(Color.RED);
        
        JPanel painel8 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel8.setBackground(Color.BLACK);
        painel8.add(lCep);
        painel8.add(campoCep);
        
        JLabel lTelefone = new JLabel("Telefone: ");
        lTelefone.setFont(fonte);
        lTelefone.setForeground(Color.RED);
        
        JPanel painel9 = new JPanel(new FlowLayout(FlowLayout.LEADING));
        painel9.setBackground(Color.BLACK);
        painel9.add(lTelefone);
        painel9.add(campoTelefone);
        
        Container cBotaoS = new Container();
        cBotaoS.setLayout(new FlowLayout(FlowLayout.LEADING));
        botaoSalvar.setBackground(Color.RED);
        cBotaoS.add(botaoSalvar);
        
        Container cBotaoV = new Container();
        cBotaoV.setLayout(new FlowLayout(FlowLayout.LEADING));
        botaoVoltar.setBackground(Color.RED);
        cBotaoV.add(botaoVoltar);
        
        painelPrincipalF.add(painel2);
        painelPrincipalF.add(painel3);
        painelPrincipalF.add(painel4);
        painelPrincipalF.add(painel5);
        painelPrincipalF.add(painel6);
        painelPrincipalF.add(painel7);
        painelPrincipalF.add(painel8);
        painelPrincipalF.add(painel9);
        painelPrincipalF.add(cBotaoS);
        painelPrincipalF.add(cBotaoV);
        
        c.add(painel1);
        c.add(painelPrincipalF);
        
        botaoVoltar.addActionListener(new Voltar());
        botaoSalvar.addActionListener(new EventoBotaoSalvar());
        
        itemConsultarFornecedor.addActionListener(new EventoConsultarDadosFornecedor());
        itemDeletarFornecedor.addActionListener(new EventoDeletarFornecedor());
        
        itemNovoLivro.addActionListener(new NovoLivro());
        itemConsultarLivro.addActionListener(new EventoConsultarDadosLivro());
        itemDeletarLivro.addActionListener(new EventoDeletarLivro());
    }//fim construtor
}
