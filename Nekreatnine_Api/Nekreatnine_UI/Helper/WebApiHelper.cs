using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Http;
using System.Text;
using System.Threading.Tasks;

namespace Nekreatnine_UI.Helper
{
    public class WebApiHelper
    {

        private HttpClient client { get; set; }

        private string route { get; set; }

        public WebApiHelper(string uri, string route)
        {
            client = new HttpClient();
            client.BaseAddress = new Uri(uri);
            this.route = route;
        }

        public HttpResponseMessage GetResponseMessage()
        {
            return client.GetAsync(route).Result;
        }



        public HttpResponseMessage GetActionResponseMessage(string action)
        {
            // api/Korisnici/Filter/
            return client.GetAsync(route + "/" + action).Result;
        }

        public HttpResponseMessage GetActionResponseMessage(string action, string parameter = "")
        {
            // api/Korisnici/Filter/{KorisnickoIme}
            return client.GetAsync(route + "/" + action + "/" + parameter).Result;
        }

        public HttpResponseMessage GetActionResponseMessage(string action, int parameter)
        {
            // api/Korisnici/Filter/{KorisnickoIme}
            return client.GetAsync(route + "/" + action + "/" + parameter).Result;
        }



        public HttpResponseMessage GetActionResponseMessage(string action, int parameter1, int parameter2)
        {
            // api/Korisnici/Filter/{parametar1}/{parametar2}
            return client.GetAsync(route + "/" + action + "/" + parameter1 + "/" + parameter2).Result;
        }

        public HttpResponseMessage PostResponseMessage(Object obj)
        {
            return client.PostAsJsonAsync(route, obj).Result;
        }

        //POST: api/Kontroler/id
        public HttpResponseMessage PostResponseMessage(string action, Object obj)
        {
            return client.PostAsJsonAsync(route + "/" + action, obj).Result;
        }

        //Brisanje sadrzaja po Id 
        public HttpResponseMessage DeleteResponseMessage(int id)
        {
            // DELETE api/Korisnik/KorisnikId
            return client.DeleteAsync(route + "/" + id).Result;
        }

        // (int id, KategorijeProizvoda kategorijeProizvoda)
        //POST: api/Kontroler/id
        public HttpResponseMessage PutResponseMessage(int id, Object obj)
        {
            // PUT api/Korisnik/KorisnikId
            return client.PutAsJsonAsync(route + "/" + id, obj).Result;
        }
    }
}
