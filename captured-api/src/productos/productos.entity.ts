import { Entity, PrimaryGeneratedColumn, Column } from 'typeorm';

@Entity('products')
export class ProductsEntity{

    @PrimaryGeneratedColumn()
    id_producto: number;

    @Column()
    prod_idHTML: number;  //El id del producto en html


}