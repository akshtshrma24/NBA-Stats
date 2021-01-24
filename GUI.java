import org.json.JSONException;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.*;
public class GUI
{
    public GUI()
    {
        JFrame frame = new JFrame("NBA Stats");
        frame.setSize(400,300);
        frame.setLayout(new BorderLayout());

        JTextArea textBox = new JTextArea(1,10);
        textBox.setVisible(true);

        JTextArea textBox2 = new JTextArea(10,30);
        textBox2.setEditable(false);
        textBox.setVisible(true);

        JPanel panel1 = new JPanel();
        panel1.add(textBox);
        panel1.setLayout(new FlowLayout());

        JPanel panel2 = new JPanel();
        panel2.add(textBox2);

        JButton send = new JButton("Send");
        panel1.add(send);

        frame.add(panel1, BorderLayout.NORTH);
        frame.add(panel2, BorderLayout.SOUTH);

        class sender implements ActionListener
        {
            public void actionPerformed(ActionEvent e)
            {

                String temp = textBox.getText();
                textBox2.setText("");
                textBox.setText("");
                temp = temp.replaceAll(" ", "_");
                methodsToGetData toGetData = new methodsToGetData();
                try {
                    textBox2.append(toGetData.getData(temp));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                } catch (JSONException jsonException) {
                    jsonException.printStackTrace();
                }

            }
        }
        ActionListener sending = new sender();
        send.addActionListener(sending);

        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
