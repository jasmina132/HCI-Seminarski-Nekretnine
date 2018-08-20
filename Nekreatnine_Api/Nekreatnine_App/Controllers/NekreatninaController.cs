using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;
using System.Web.Http.Description;
using Nekreatnine_App.Models;

namespace Nekreatnine_App.Controllers
{
    public class NekreatninaController : ApiController
    {
        private nekreatnineEntities db = new nekreatnineEntities();

        // GET: api/Nekreatnina
        public IQueryable<Nekreatnina> GetNekreatnina()
        {
            return db.Nekreatnina;
        }

        // GET: api/Nekreatnina/5
        [ResponseType(typeof(Nekreatnina))]
        public IHttpActionResult GetNekreatnina(int id)
        {
            Nekreatnina nekreatnina = db.Nekreatnina.Find(id);
            if (nekreatnina == null)
            {
                return NotFound();
            }

            return Ok(nekreatnina);
        }

        // PUT: api/Nekreatnina/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutNekreatnina(int id, Nekreatnina nekreatnina)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != nekreatnina.NekreatninaId)
            {
                return BadRequest();
            }

            db.Entry(nekreatnina).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!NekreatninaExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        [Route("api/Nekreatnina/GetNekreatnine/")]
        [ResponseType(typeof(Nekreatnina))]
        public IHttpActionResult GetNekreatnine()
        {
            var nar = db.get_Nekreatnine_HCI().ToList();
            if (nar == null)
            {
                return NotFound();
            }
            return Ok(nar);
        }

        [Route("api/Nekreatnina/GetNekreatnineBySearch/{nazivGrada}/{balkon}/{grijanje}/{parking}/{plin}/{vrstaNekreatnine}")]
        [ResponseType(typeof(Nekreatnina))]
        public IHttpActionResult GetNekreatnineBySearch(string nazivGrada, bool balkon, bool grijanje, bool parking, bool plin,
            int vrstaNekreatnine)
        {
            var nar = db.get_Nekreatnine_HCI_BySearch(nazivGrada, balkon, grijanje, parking, plin, vrstaNekreatnine).ToList();
            if (nar == null)
            {
                return NotFound();
            }
            return Ok(nar);
        }

        // POST: api/Nekreatnina
        [ResponseType(typeof(Nekreatnina))]
        public IHttpActionResult PostNarudzbe(Nekreatnina nekreatnina)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }


            nekreatnina.NekreatninaId =
                Convert.ToInt32(
                    db.insert_Nekreatnine_HCI(nekreatnina.VrstaId, nekreatnina.LokacijaId, nekreatnina.Cijena, nekreatnina.Velicina, nekreatnina.Opis).FirstOrDefault());

            foreach (SlikeNekreatnina slike in nekreatnina.SlikeNekreatnina)
            {
                db.insert_SlikeNekreatnine_HCI(nekreatnina.NekreatninaId, slike.Slika);
            }

            foreach (StanApartman stan in nekreatnina.StanApartman)
            {
                db.insert_StanApartman_HCI(nekreatnina.NekreatninaId, stan.CijenaKvadrata, stan.Uknjizeno, stan.Opremljenost, stan.Grijanje, stan.Lift, stan.Balkon, stan.Parking, stan.Plin, stan.KablovksaTv);
            }

            /*     nekreatnina.NarudzbaID =
                     Convert.ToInt32(
                         db.insert_Narudzbe_HCI(narudzbe.BrojRacuna, narudzbe.KupacID, narudzbe.Datum).FirstOrDefault());

                 foreach (NarudzbaStavke stavke in narudzbe.NarudzbaStavke)
                 {
                     db.insert_NarudzbaStavke_HCI(narudzbe.NarudzbaID, stavke.AlbumID, stavke.Kolicina);
                 }*/



              return CreatedAtRoute("DefaultApi", new { id = nekreatnina.NekreatninaId }, nekreatnina);
          //  return Ok(nekreatnina);
            
        }


        // DELETE: api/Nekreatnina/5
        [ResponseType(typeof(Nekreatnina))]
        public IHttpActionResult DeleteNekreatnina(int id)
        {
            Nekreatnina nekreatnina = db.Nekreatnina.Find(id);
            if (nekreatnina == null)
            {
                return NotFound();
            }

            db.Nekreatnina.Remove(nekreatnina);
            db.SaveChanges();

            return Ok(nekreatnina);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool NekreatninaExists(int id)
        {
            return db.Nekreatnina.Count(e => e.NekreatninaId == id) > 0;
        }
    }
}