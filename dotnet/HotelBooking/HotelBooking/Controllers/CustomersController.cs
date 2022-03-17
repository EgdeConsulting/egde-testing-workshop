#nullable disable
using Microsoft.AspNetCore.Mvc;
using HotelBooking.Models;
using HotelBooking.Services;

namespace HotelBooking.Controllers
{
    [Route("[controller]")]
    [ApiController]
    public class CustomersController : ControllerBase
    {
        private readonly ICustomerService _customerService;

        public CustomersController(ICustomerService customerService)
        {
            _customerService = customerService;
        }
        
        [HttpGet]
        public async Task<ActionResult<List<Customer>>> GetCustomers()
        {
            return await _customerService.GetCustomers();
        }
        
        [HttpGet("{id}")]
        public async Task<ActionResult<Customer>> GetCustomerById(int id)
        {
            var customer = await _customerService.GetCustomerById(id);
            return customer == null ? NotFound("Customer was not found") : Ok(customer);
        }
        
        [HttpPost]
        public async Task<ActionResult<Customer>> CreateCustomer(Customer customer)
        {
            var createdCustomer = await _customerService.CreateCustomer(customer);
            return Ok(createdCustomer);
        }

        [HttpGet("paying/{count}")]
        public async Task<ActionResult<List<CustomerWithTotalPayment>>> TakeHighestPayingCustomers(int count)
        {
            var payingCustomers = await _customerService.TakeHighestPayingCustomers(count);
            return Ok(payingCustomers);
        }
    }
}
