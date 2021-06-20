<?php

use App\Http\Controllers\CategoriasController;
use App\Http\Controllers\ComentariosController;
use App\Http\Controllers\EquiposController;
use App\Http\Controllers\EstadosController;
use App\Http\Controllers\IncidenciasController;
use App\Http\Controllers\SessionController;
use App\Http\Controllers\UsuariosController;
use App\Models\User;
use Illuminate\Support\Facades\Route;

/*
|--------------------------------------------------------------------------
| Web Routes
|--------------------------------------------------------------------------
|
| Here is where you can register web routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| contains the "web" middleware group. Now create something great!
|
*/

/*
 * Direcciones básicas
 */

Auth::routes();

Route::get('/home', [App\Http\Controllers\HomeController::class, 'index'])
    ->name('home');

Route::get('/', function () {
    return redirect('/home');
});

Route::get('/info', function () {
    return view('welcome');
})
    ->name('info');

/*
 * Administración de los diferentes componentes
 */

Route::prefix('/incidencias')->group(function () {
    Route::get('/', [IncidenciasController::class, 'index'])
        ->name('incidencias');
    Route::get('/edit/{id}/view', [IncidenciasController::class, 'updateView']);
    Route::put('/edit/{id}', [IncidenciasController::class, 'update']);
    Route::delete('/delete/{id}', [IncidenciasController::class, 'delete']);
});

Route::prefix('/usuarios')->group(function () {
    Route::get('/', [UsuariosController::class, 'index'])
        ->name('usuarios');
    Route::delete('/delete/{id}', [UsuariosController::class, 'delete']);
});

Route::prefix('/comentarios')->group(function () {
    Route::get('/', [ComentariosController::class, 'index'])
        ->name('comentarios');
    Route::delete('/delete/{id}', [ComentariosController::class, 'delete']);
});

Route::prefix('/categorias')->group(function () {
    Route::get('/', [CategoriasController::class, 'index'])
        ->name('categorias');
    Route::get('/create/view', [CategoriasController::class, 'view']);
    Route::post('/create', [CategoriasController::class, 'insert']);
    Route::get('/edit/{id}/view', [CategoriasController::class, 'view']);
    Route::put('/edit/{id}', [CategoriasController::class, 'update']);
    Route::delete('/delete/{id}', [CategoriasController::class, 'delete']);
});

Route::prefix('/equipos')->group(function () {
    Route::get('/', [EquiposController::class, 'index'])
        ->name('equipos');
    Route::get('/create/view', [EquiposController::class, 'view']);
    Route::post('/create', [EquiposController::class, 'insert']);
    Route::get('/edit/{id}/view', [EquiposController::class, 'view']);
    Route::put('/edit/{id}', [EquiposController::class, 'update']);
    Route::delete('/delete/{id}', [EquiposController::class, 'delete']);
});

Route::prefix('/estados')->group(function () {
    Route::get('/', [EstadosController::class, 'index'])
        ->name('estados');
    Route::get('/create/view', [EstadosController::class, 'view']);
    Route::post('/create', [EstadosController::class, 'insert']);
    Route::get('/edit/{id}/view', [EstadosController::class, 'view']);
    Route::put('/edit/{id}', [EstadosController::class, 'update']);
    Route::delete('/delete/{id}', [EstadosController::class, 'delete']);
});

/*
 * Testing
 */

Route::get('/sesion', [SessionController::class, 'view']);

Route::get('/hola', function () {
    $pdo = DB::connection()->getPdo();
    if($pdo)
    {
        echo "Connected successfully to database " . DB::connection()->getDatabaseName();
    } else {
        echo "You are not connected to database";
    }
    return "Hola ciudadano";
});

Route::get('/adios', function () {
    return "Adiós ciudadano";
});
