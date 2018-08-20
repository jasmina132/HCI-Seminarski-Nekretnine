using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using Nekreatnine_App.Models;
using Nekreatnine_UI.Helper;

namespace Nekreatnine_UI
{
    public partial class DodavanjeNekreatnine : Form
    {
        private WebApiHelper nekreatnineService = new WebApiHelper("http://localhost:51385/", "api/Nekreatnina");
        private WebApiHelper vrsteNekreatninaService = new WebApiHelper("http://localhost:51385/", "api/VrstaNekreatnine");
        private WebApiHelper lokacijeService = new WebApiHelper("http://localhost:51385/", "api/Lokacija");

        private Nekreatnina nekreatnina = new Nekreatnina();

        public DodavanjeNekreatnine()
        {
            InitializeComponent();

             BindVrsteNekreatnina();
             BindLokacije();
        }

        private void BindLokacije()
        {

            HttpResponseMessage response = lokacijeService.GetResponseMessage();
            if (response.IsSuccessStatusCode)
            {
                cmb_lokacija.DataSource = response.Content.ReadAsAsync<List<Lokacija>>().Result;
                cmb_lokacija.DisplayMember = "Mjesto";
                cmb_lokacija.ValueMember = "LokacijaId";
            }
            else
            {
                MessageBox.Show("Greska u komunikaciji sa serverom!");
            }

        }

        private void BindVrsteNekreatnina()
        {
            HttpResponseMessage response = vrsteNekreatninaService.GetResponseMessage();
            if (response.IsSuccessStatusCode)
            {
                cmb_vrsteNekreatnine.DataSource = response.Content.ReadAsAsync<List<VrstaNekreatnine>>().Result;
                cmb_vrsteNekreatnine.DisplayMember = "Naziv";
                cmb_vrsteNekreatnine.ValueMember = "VrstaId";

                MessageBox.Show("Uspjesno dobavljene vrste!");
            }
            else
            {
                MessageBox.Show("Greska u komunikaciji sa serverom!");
            }
        }

        private void btn_dodajSliku_Click(object sender, EventArgs e)
        {
            try
            {
                SlikeNekreatnina slika = new SlikeNekreatnina();
                openFileDialog.ShowDialog();
                slikaInputtxt.Text = openFileDialog.FileName;

                Image image = Image.FromFile(slikaInputtxt.Text);

                MemoryStream ms = new MemoryStream();
                image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);

                slika.Slika = ms.ToArray();

   
                slika.Slika = ms.ToArray();
                    pictureBox1.Image = image;
                nekreatnina.SlikeNekreatnina = new List<SlikeNekreatnina>();
                nekreatnina.SlikeNekreatnina.Add(slika);

            }
            catch (Exception)
            {
                MessageBox.Show("Fajl koji ste odabrali nije slika!", "Greška", MessageBoxButtons.OK, MessageBoxIcon.Error);
            }
        }

        private void btn_dodaj_Click(object sender, EventArgs e)
        {
            nekreatnina.VrstaId = Convert.ToInt32(cmb_vrsteNekreatnine.SelectedValue);
            nekreatnina.LokacijaId = Convert.ToInt32(cmb_lokacija.SelectedValue);
            nekreatnina.Cijena = Convert.ToInt32(txt_cijena.Text);
            nekreatnina.Velicina = Convert.ToInt32(txt_velicina.Text);
            nekreatnina.Opis = txt_opis.Text;
            
            StanApartman stan = new StanApartman();
            stan.CijenaKvadrata = Convert.ToInt32(txt_cijenaKvadrata.Text);
            stan.Uknjizeno = rb_uknjizeno.Checked;
            stan.Opremljenost = txt_Opremljenost.Text;
            stan.Grijanje = txt_grijanje.Checked;
            stan.Lift = rb_lift.Checked;
            stan.Balkon = txt_balkon.Checked;
            stan.Plin = rb_Plin.Checked;
            stan.Parking = rb_Parking.Checked;
            stan.KablovksaTv = rb_Kablovska.Checked;

            nekreatnina.StanApartman.Add(stan);


            HttpResponseMessage response = nekreatnineService.PostResponseMessage(nekreatnina);

            if (response.IsSuccessStatusCode)
            {
                MessageBox.Show("Uspjesno dodana nekreatnina");

            }
            else
                MessageBox.Show("Greska u komunikaciji sa serverom ");


        }

        private void btn_slika_2_Click(object sender, EventArgs e)
        {
            SlikeNekreatnina slika = new SlikeNekreatnina();

            openFileDialog.ShowDialog();
            txt_slika_2.Text = openFileDialog.FileName;

            Image image = Image.FromFile(txt_slika_2.Text);

            MemoryStream ms = new MemoryStream();
            image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);

            slika.Slika = ms.ToArray();

            slika.Slika = ms.ToArray();
            nekreatnina.SlikeNekreatnina.Add(slika);
        }

        private void bnt_slika_3_Click(object sender, EventArgs e)
        {
            SlikeNekreatnina slika = new SlikeNekreatnina();

            openFileDialog.ShowDialog();
            txt_slika_3.Text = openFileDialog.FileName;

            Image image = Image.FromFile(txt_slika_3.Text);

            MemoryStream ms = new MemoryStream();
            image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);

            slika.Slika = ms.ToArray();

            slika.Slika = ms.ToArray();
            nekreatnina.SlikeNekreatnina.Add(slika);
        }

        private void btn_slika4_Click(object sender, EventArgs e)
        {
            SlikeNekreatnina slika = new SlikeNekreatnina();

            openFileDialog.ShowDialog();
            txt_slika_4.Text = openFileDialog.FileName;

            Image image = Image.FromFile(txt_slika_4.Text);

            MemoryStream ms = new MemoryStream();
            image.Save(ms, System.Drawing.Imaging.ImageFormat.Jpeg);

            slika.Slika = ms.ToArray();

            slika.Slika = ms.ToArray();
            nekreatnina.SlikeNekreatnina.Add(slika);
        }
    }
}
