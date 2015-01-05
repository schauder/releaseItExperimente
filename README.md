# Getting Started

## EvilServer

Start `EvilServerStarter` class. Default EvilServer port is 8000.

Endpoints

- `/ok` Return 200 StatusCode and _Hello, World!_ unless defective flag is set (`/wait` behaviour with 5 seconds)
- `/wait/<sec>` Wait `<sec>` seconds then return 200 StatusCode and _Hello, World!_
- `/500` Throw RuntimeException
- `/defective/[true|false]` set defective flag to true / false
- `/slowresponse` Write one byte per second and flush each iteration
- `/slowresponsenoflush` Write one byte per second but never flush before finish

