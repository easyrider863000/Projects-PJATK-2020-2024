using APBD_4.Models;

namespace APBD_4.Services
{
    public interface IDBservice
    {
        Task<IList<Animal>> GetListAnimal();

        Task<IList<Animal>> GetListAnimal(string orderBy);

        Task<string> PostAnimal(Animal animal);

        Task<string> DeleteAnimal(int idAnimal);
    }
}
