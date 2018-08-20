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
    public class VrstaNekreatnineController : ApiController
    {
        private nekreatnineEntities db = new nekreatnineEntities();

        // GET: api/VrstaNekreatnine
        public IQueryable<VrstaNekreatnine> GetVrstaNekreatnine()
        {
            return db.VrstaNekreatnine;
        }

        // GET: api/VrstaNekreatnine/5
        [ResponseType(typeof(VrstaNekreatnine))]
        public IHttpActionResult GetVrstaNekreatnine(int id)
        {
            VrstaNekreatnine vrstaNekreatnine = db.VrstaNekreatnine.Find(id);
            if (vrstaNekreatnine == null)
            {
                return NotFound();
            }

            return Ok(vrstaNekreatnine);
        }

        // PUT: api/VrstaNekreatnine/5
        [ResponseType(typeof(void))]
        public IHttpActionResult PutVrstaNekreatnine(int id, VrstaNekreatnine vrstaNekreatnine)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != vrstaNekreatnine.VrstaId)
            {
                return BadRequest();
            }

            db.Entry(vrstaNekreatnine).State = EntityState.Modified;

            try
            {
                db.SaveChanges();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!VrstaNekreatnineExists(id))
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

        // POST: api/VrstaNekreatnine
        [ResponseType(typeof(VrstaNekreatnine))]
        public IHttpActionResult PostVrstaNekreatnine(VrstaNekreatnine vrstaNekreatnine)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            db.VrstaNekreatnine.Add(vrstaNekreatnine);
            db.SaveChanges();

            return CreatedAtRoute("DefaultApi", new { id = vrstaNekreatnine.VrstaId }, vrstaNekreatnine);
        }

        // DELETE: api/VrstaNekreatnine/5
        [ResponseType(typeof(VrstaNekreatnine))]
        public IHttpActionResult DeleteVrstaNekreatnine(int id)
        {
            VrstaNekreatnine vrstaNekreatnine = db.VrstaNekreatnine.Find(id);
            if (vrstaNekreatnine == null)
            {
                return NotFound();
            }

            db.VrstaNekreatnine.Remove(vrstaNekreatnine);
            db.SaveChanges();

            return Ok(vrstaNekreatnine);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool VrstaNekreatnineExists(int id)
        {
            return db.VrstaNekreatnine.Count(e => e.VrstaId == id) > 0;
        }
    }
}