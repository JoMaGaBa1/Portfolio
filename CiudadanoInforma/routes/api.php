<?php

use App\Http\Controllers\Api\CategoriasController;
use App\Http\Controllers\Api\EquiposController;
use App\Http\Controllers\Api\EstadosController;
use App\Http\Controllers\Api\IncidenciasController;
use App\Http\Controllers\Api\UsuariosController;
use App\Http\Controllers\AuthController;
use App\Http\Resources\EstadoResource;
use App\Http\Resources\UserResource;
use App\Models\Equipo;
use App\Models\Estado;
use App\Models\User;
use Carbon\Carbon;
use Illuminate\Http\Request;
use Illuminate\Http\Resources\Json\JsonResource;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:sanctum')->get('/user', function (Request $request) {
    $usuario = $request->user();
    return response()->json([
        'id' => $usuario->id,
        'name' => $usuario->name,
        'email' => $usuario->email
    ]);
});

/*
 * Sesiones
 */

Route::prefix('/auth')->group(function () {
    Route::post('/login', [AuthController::class, 'login']);
    Route::delete('/logout', [AuthController::class, 'logout'])
        ->middleware('auth:sanctum');
});

Route::prefix('/usuarios')->group(function () {
    Route::get('/', [UsuariosController::class, 'index']);
    Route::get('/{id}', [UsuariosController::class, 'extractWithId']);
    Route::post('/register', function (Request $request) {
        $id = DB::table('users')->insertGetId([
            'name' => $request->name,
            'email' => $request->email,
            'password' => Hash::make($request->password),
            'admin' => false,
            'created_at' => Carbon::now()->toDateTimeString(),
            'updated_at' => Carbon::now()->toDateTimeString()
        ]);
        return response()->json(new UserResource(User::findOrFail($id)));
    });
});

/*
 * Manejo de los diferentes componentes
 */

Route::prefix('/incidencias')->group(function () {
    Route::get('/', [IncidenciasController::class, 'index']);
    Route::get('/{id}', [IncidenciasController::class, 'extractWithId']);
    Route::post('/create', [IncidenciasController::class, 'insert']);
    Route::post('/{id}/comment', [IncidenciasController::class, 'comment']);
});

Route::prefix('/categorias')->group(function () {
    Route::get('/', [CategoriasController::class, 'index']);
    Route::get('/{id}', [CategoriasController::class, 'extractWithId']);
});

Route::prefix('/equipos')->group(function () {
    Route::get('/', [EquiposController::class, 'index']);
    Route::get('/{id}', [EquiposController::class, 'extractWithId']);
});

Route::prefix('/estados')->group(function () {
    Route::get('/', [EstadosController::class, 'index']);
    Route::get('/{id}', [EstadosController::class, 'extractWithId']);
});
