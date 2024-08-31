import { ProductosService } from './productos.service';
/*
https://docs.nestjs.com/modules
*/

import { Module } from '@nestjs/common';

@Module({
    imports: [],
    controllers: [],
    providers: [
        ProductosService, ],
})
export class ProductosModule { }
