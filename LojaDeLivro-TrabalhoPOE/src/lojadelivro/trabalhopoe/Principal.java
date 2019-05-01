package lojadelivro.trabalhopoe;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Principal extends JFrame
{
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
    
    private class NovoFornecedor implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            new FormularioNovoFornecedor().setVisible(true);
            dispose();
        }
    }
       //classe interna que trata o evento no item de menu do professor
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
            ArrayList<String> dadosFornecedores = acessoArquivo.lerArquivo("arquivoFornecedores.txt");
            //=************** CHAMANDO O FRAME DE TABELA ***************==//
            new TabelaDadosDeletarFornecedor(dadosFornecedores);
        }
    }
    public Principal()
    {
        super("Sistema de Cadastro");
        setSize(371,364);
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
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
        
        JMenuItem itemNovoFornecedor = new JMenuItem("Novo");
        JMenuItem itemConsultarFornecedor = new JMenuItem("Consultar");
        JMenuItem itemDeletarFornecedor = new JMenuItem("Deletar");
        
        menuFornecedor.add(itemNovoFornecedor);
        menuFornecedor.add(itemConsultarFornecedor);
        menuFornecedor.add(itemDeletarFornecedor);
        
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.CENTER));
        
        JPanel painelPrincipal = new JPanel(new GridLayout(2,1));
        painelPrincipal.setBorder(new TitledBorder
        (BorderFactory.createLineBorder(Color.RED), "Opções Principais"));
        
        painelPrincipal.setBackground(Color.BLACK);
        
        JLabel rotuloPrincipal = new JLabel("Sistema de cadastro");
        
        rotuloPrincipal.setHorizontalAlignment(JLabel.CENTER);
        rotuloPrincipal.setForeground(Color.RED);
        rotuloPrincipal.setFont(new Font("Garamond", Font.BOLD, 18));
        
        ImageIcon imagem= new ImageIcon(getClass().getResource("/img/logo1.jpg"));
        JLabel rotuloImagem = new JLabel(imagem);
        
        painelPrincipal.add(rotuloPrincipal);
        painelPrincipal.add(rotuloImagem);
        
        c.add(painelPrincipal);
        
        itemNovoLivro.addActionListener(new NovoLivro());
        itemConsultarLivro.addActionListener(new EventoConsultarDadosLivro());
        itemDeletarLivro.addActionListener(new EventoDeletarLivro());
        
        itemNovoFornecedor.addActionListener(new NovoFornecedor());
        itemConsultarFornecedor.addActionListener(new EventoConsultarDadosFornecedor());
        itemDeletarFornecedor.addActionListener(new EventoDeletarFornecedor());
    }
    
    public static void main(String[] args) 
    {
        new Principal().setVisible(true);
    }
}
