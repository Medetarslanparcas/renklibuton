//1220505060
//medet arslanparçası



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CustomButton extends JButton {
    private String graphQLSchema;
    private Color activeRenk;
    private Color pasifRenk;
    private boolean aktifDurum;

    public CustomButton(String text) {
        super(text);
        this.graphQLSchema = ""; // Gerçek şema adresini ekleyin
        this.activeRenk = Color.GREEN;
        this.pasifRenk = Color.GRAY;
        this.aktifDurum = false;

        pasifDurumuAyarla();
        addActionListener(new ButonTiklamaListener());
    }

    public void setGraphQLSchema(String schema) {
        this.graphQLSchema = schema;
    }

    public void setAktifRenk(Color color) {
        this.activeRenk = color;
    }

    public void setPasifRenk(Color color) {
        this.pasifRenk = color;
    }

    private void pasifDurumuAyarla() {
        setBackground(pasifRenk);
        setText("Pasif");
        aktifDurum = false;
    }

    private void aktifDurumuAyarla() {
        setBackground(activeRenk);
        setText("Aktif");
        aktifDurum = true;
    }

    private class ButonTiklamaListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (aktifDurum) {
                mevcutHaricTumButonlarPasifDurumuAyarla();
            } else {
                aktifDurumuAyarla();
                // Burada kendi belirlediğiniz GraphQL şemasında bir mutation çalıştırabilirsiniz.
                // Örneğin: runGraphQLMutation(graphQLSchema);
                System.out.println("Mutation çalıştırıldı: " + graphQLSchema);
            }
        }
    }

    private void mevcutHaricTumButonlarPasifDurumuAyarla() {
        for (Component component : getParent().getComponents()) {
            if (component instanceof CustomButton && component != this) {
                ((CustomButton) component).pasifDurumuAyarla();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Buton Paneli");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLayout(new GridLayout(4, 4, 10, 10)); // Boşluk eklenmiştir.

            CustomButton[] butonlar = new CustomButton[16];
            for (int i = 0; i < 16; i++) {
                butonlar[i] = new CustomButton("Buton " + (i + 1));
                // İstenirse buton text, renk, simge vb. özellikleri burada dinamik olarak ayarlanabilir.
                frame.add(butonlar[i]);
            }

            // Örnek şema adresleri, gerçek adreslerle değiştirilmelidir.
            butonlar[0].setGraphQLSchema("https://api.example.com/graphql");
            butonlar[1].setGraphQLSchema("https://api.anotherexample.com/graphql");
            butonlar[2].setGraphQLSchema("https://api.yetanotherexample.com/graphql");

            frame.setSize(400, 400);
            frame.setVisible(true);
        });
    }
}