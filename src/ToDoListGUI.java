import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ToDoListGUI extends JFrame {
    private TaskManager taskManager;
    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    public ToDoListGUI() {
        taskManager = new TaskManager();
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);

        // Configurações da Janela
        setTitle("To-Do List - Gerenciador de Tarefas");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Fonte personalizada
        Font font = new Font("Arial", Font.PLAIN, 16);
        
        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(Color.WHITE);
        
        // Área de rolagem para a lista de tarefas
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Estilo da lista
        taskList.setFont(font);
        taskList.setBackground(Color.decode("#f0f8ff"));
        taskList.setBorder(BorderFactory.createLineBorder(Color.decode("#4682B4")));
        
        // Painel inferior com botões
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());
        inputPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        inputPanel.setBackground(Color.WHITE);
        
        // Botão Adicionar
        JButton addButton = new JButton("Adicionar");
        addButton.setFont(font);
        addButton.setBackground(Color.decode("#4682B4"));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Botão Remover
        JButton removeButton = new JButton("Remover");
        removeButton.setFont(font);
        removeButton.setBackground(Color.decode("#FF6347"));
        removeButton.setForeground(Color.WHITE);
        removeButton.setFocusPainted(false);
        removeButton.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Painel dos botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2, 10, 0));
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        
        inputPanel.add(buttonPanel, BorderLayout.SOUTH);
        panel.add(inputPanel, BorderLayout.SOUTH);
        
        // Ações dos botões
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String task = JOptionPane.showInputDialog(ToDoListGUI.this, "Digite a tarefa:", "Nova Tarefa", JOptionPane.PLAIN_MESSAGE);
                if (task != null && !task.trim().isEmpty()) {
                    taskManager.addTask(task);
                    listModel.addElement(task);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = taskList.getSelectedIndex();
                if (selectedIndex != -1) {
                    taskManager.removeTask(selectedIndex);
                    listModel.remove(selectedIndex);
                }
            }
        });

        add(panel);
        setVisible(true);
    }
}
