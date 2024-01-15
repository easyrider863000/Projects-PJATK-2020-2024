using APBD_4.Models;
using System.Data.SqlClient;
using System.Windows.Input;

namespace APBD_4.Services
{
    public class DBservice : IDBservice
    {
        private const string ConnectionStr = "Server=LAPTOP-BR05KG5L\\TEST_SQL;Database=AnimalDB;Trusted_Connection=True;";

        public async Task<IList<Animal>> GetListAnimal()
        {
            List<Animal> resultList = new();

            string sql = "SELECT * FROM Animal ORDER BY Name";
            await using SqlConnection connection = new(ConnectionStr);
            await using SqlCommand command = new(sql, connection);

            await connection.OpenAsync();

            await using SqlDataReader reader = await command.ExecuteReaderAsync();

            while (await reader.ReadAsync())
            {
                resultList.Add(new Animal
                { 
                    IdAnimal = int.Parse(reader["IdAnimal"].ToString()),
                    Name = reader["Name"].ToString(),
                    Description = reader["Description"].ToString(),
                    Category = reader["Category"].ToString(),
                    Area = reader["Area"].ToString()
                });
            }

            return resultList;
        }

        public async Task<IList<Animal>> GetListAnimal(string orderBy)
        {
            List<Animal> resultList = new();

            string sql = $"SELECT * FROM Animal ORDER BY {orderBy}";
            await using SqlConnection connection = new(ConnectionStr);
            await using SqlCommand command = new(sql, connection);

            await connection.OpenAsync();

            await using SqlDataReader reader = await command.ExecuteReaderAsync();

            while (await reader.ReadAsync())
            {
                resultList.Add(new Animal
                {
                    IdAnimal = int.Parse(reader["IdAnimal"].ToString()),
                    Name = reader["Name"].ToString(),
                    Description = reader["Description"] == DBNull.Value ? null:reader["Description"].ToString(),
                    Category = reader["Category"].ToString(),
                    Area = reader["Area"].ToString()
                });
            }

            return resultList;
        }

        public async Task<string> PostAnimal(Animal animal) 
        {
            
            string outStr = "";
            string sql = "INSERT INTO Animal (Name, Description, Category, Area)" +
                         "VALUES(@Name, @Description, @Category, @Area)";
            await using SqlConnection connection = new(ConnectionStr);
            Console.WriteLine("1");
            await using SqlCommand command = new(sql, connection);
            Console.WriteLine("2");
            command.Parameters.AddWithValue("Name", animal.Name);
            command.Parameters.Add(new SqlParameter("Description", animal.Description is null?DBNull.Value:animal.Description){IsNullable = true});
            command.Parameters.AddWithValue("Category", animal.Category);
            command.Parameters.AddWithValue("Area", animal.Area);
            Console.WriteLine("3");
            await connection.OpenAsync();
            Console.WriteLine("4");
            await command.ExecuteNonQueryAsync();
            Console.WriteLine("5");
            Console.WriteLine(outStr);

            return outStr;

        }

        public async Task<string> DeleteAnimal(int idAnimal)
        {
            string outStr = "";
            string sql = "DELETE FROM Animal WHERE IdAnimal=@idAnimal";
            await using SqlConnection connection = new(ConnectionStr);
            Console.WriteLine("1");
            await using SqlCommand command = new(sql, connection);
            Console.WriteLine("2");
            command.Parameters.AddWithValue("idAnimal", idAnimal);
            Console.WriteLine("3");
            await connection.OpenAsync();
            Console.WriteLine("4");
            await command.ExecuteNonQueryAsync();
            Console.WriteLine("5");
            Console.WriteLine(outStr);

            return outStr;
        }
    }
}
