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
    public class FavoritController : ApiController
    {
        private nekreatnineEntities db = new nekreatnineEntities();

        // GET: api/Favorit
        public IQueryable<Favorit> GetFavorit()
        {
            return db.Favorit;
        }

        // GET: api/Favorit/5
        [ResponseType(typeof(Favorit))]
        [Route("api/Favorit/GetFavoritByUserId/{id}")]
        public IHttpActionResult GetFavoritByUserId(int id)
        {
            List<Favorit> favoriti = db.Favorit.Where(x=>x.KorisnikId == id).ToList();
            List<Favorit> tempFavorits = new List<Favorit>();
            if (favoriti == null)
            {
                return NotFound();
            }

            bool found = false;

            foreach (Favorit favorit in favoriti)
            {
                foreach (Favorit tempFavorit in tempFavorits)
                {
                    if (favorit.NekreatninaId == tempFavorit.NekreatninaId)
                    {
                        found = true;
                    }
                }
                if (!found)
                {
                    tempFavorits.Add(favorit);
                }
                else
                {
                    found = false;
                }
            }

            return Ok(tempFavorits);
        }

        // GET: api/Favorit/5
        [ResponseType(typeof(Favorit))]
        public IHttpActionResult GetFavorit(int id)
        {
            Favorit favorit = db.Favorit.Find(id);
            if (favorit == null)
            {
                return NotFound();
            }

            return Ok(favorit);
        }

        // PUT: api/Favorit/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutFavorit(int id, Favorit favorit)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != favorit.FavoritId)
            {
                return BadRequest();
            }

            db.Entry(favorit).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!FavoritExists(id))
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

        // POST: api/Favorit
        [ResponseType(typeof(Favorit))]
        public IHttpActionResult PostFavorit(Favorit favorit)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Favorit.Add(favorit);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = favorit.FavoritId }, favorit);
        }

        // DELETE: api/Favorit/5
        [ResponseType(typeof(Favorit))]
        public IHttpActionResult DeleteFavorit(int id)
        {
            Favorit favorit = db.Favorit.Find(id);
            if (favorit == null)
            {
                return NotFound();
            }

            db.Favorit.Remove(favorit);
            db.SaveChanges();

            return Ok(favorit);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool FavoritExists(int id)
        {
            return db.Favorit.Count(e => e.FavoritId == id) > 0;
        }
    }
}