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
    public class LokacijaController : ApiController
    {
        private nekreatnineEntities db = new nekreatnineEntities();

        // GET: api/Lokacija
        public IQueryable<Lokacija> GetLokacija()
        {
            return db.Lokacija;
        }

        
        // GET: api/Lokacija/5
        [ResponseType(typeof(Lokacija))]
        public IHttpActionResult GetLokacija(int id)
        {
            Lokacija lokacija = db.Lokacija.Find(id);
            if (lokacija == null)
            {
                return NotFound();
            }

            return Ok(lokacija);
        }

        // PUT: api/Lokacija/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutLokacija(int id, Lokacija lokacija)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != lokacija.LokacijaId)
            {
                return BadRequest();
            }

            db.Entry(lokacija).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!LokacijaExists(id))
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

        // POST: api/Lokacija
        [ResponseType(typeof(Lokacija))]
        public IHttpActionResult PostLokacija(Lokacija lokacija)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.Lokacija.Add(lokacija);

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateException)
            {
                if (LokacijaExists(lokacija.LokacijaId))
                {
                    return Conflict();
                }
                else
                {
                    throw;
                }
            }

            return CreatedAtRoute("DefaultApi", new { id = lokacija.LokacijaId }, lokacija);
        }

        // DELETE: api/Lokacija/5
        [ResponseType(typeof(Lokacija))]
        public IHttpActionResult DeleteLokacija(int id)
        {
            Lokacija lokacija = db.Lokacija.Find(id);
            if (lokacija == null)
            {
                return NotFound();
            }

            db.Lokacija.Remove(lokacija);
            db.SaveChanges();

            return Ok(lokacija);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool LokacijaExists(int id)
        {
            return db.Lokacija.Count(e => e.LokacijaId == id) > 0;
        }
    }
}