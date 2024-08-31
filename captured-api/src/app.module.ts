import { CuponesModule } from './cupones/cupones.module';
import { CuponesController } from './cupones/cupones.controller';
import { PedidosModule } from './pedidos/pedidos.module';
import { PedidosController } from './pedidos/pedidos.controller';
import { ProductosModule } from './productos/productos.module';
import { ProductosController } from './productos/productos.controller';
import { UsersModule } from './users/users.module';
import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { TypeOrmModule } from '@nestjs/typeorm';
import { join } from 'path';

@Module({
  imports: [
        TypeOrmModule.forRoot({
          "type": "mysql",
          "host": "localhost",
          "port": 3306,
          "username": "root",
          "password": "",
          "database": "capturedstyles",
          "entities": [join(__dirname, '**', '*.entity.{ts,js}')],
          "synchronize": true
        }),
        CuponesModule, 
        PedidosModule, 
        ProductosModule,
        UsersModule,],
  controllers: [
        CuponesController, 
        PedidosController, 
        ProductosController,
        AppController],
  providers: [AppService],
})
export class AppModule { }
