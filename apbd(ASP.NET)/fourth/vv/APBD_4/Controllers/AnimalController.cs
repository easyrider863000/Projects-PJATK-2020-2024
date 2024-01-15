using Microsoft.AspNetCore.Mvc;
using APBD_4.Services;
using APBD_4.Models;

namespace APBD_4.Controllers
{
    [ApiController]
    [Route("api/animal")]
    public class AnimalController : Controller
    {
        private readonly IDBservice _dBService;

        public AnimalController(IDBservice dBService)
        {
            _dBService = dBService;
        }

        [HttpGet]

        public async Task<IList<Animal>> GetListAnimals() 
        {
            return await _dBService.GetListAnimal();
        }


        [HttpGet("orderBy")]

        public async Task<IList<Animal>> GetListAnimals(string orderBy)
        {
            return await _dBService.GetListAnimal(orderBy);
        }

        [HttpPost]
        public async Task<IActionResult> GetListAnimals(Animal animal) 
        {
            await _dBService.PostAnimal(animal);
            return Created("api/animal",animal);
            //return Ok();
        }

        [HttpDelete("idAnimal")]
        public async Task<IActionResult> DeleteAnimals(int idAnimal) 
        {
            await _dBService.DeleteAnimal(idAnimal);
            return Ok("deleted animal with id "+idAnimal);
            //return Ok();
        }

    }
}
