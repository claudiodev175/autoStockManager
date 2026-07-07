import { Component, OnInit, AfterViewInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { HeaderComponent } from '../header/header.component';
import { FooterComponent } from '../footer/footer.component';


@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule, HeaderComponent, FooterComponent],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  
  // Cards
  totalProdutos = 0;
  valorTotal = 0;
  estoqueBaixo = 0;
  totalMovimentacoes = 0;
  entradas = 0;
  saidas = 0;
  crescimento = 0;
  produtosNovos = 0;

  // Tabelas
  ultimasMovimentacoes: any[] = [];
  maisVendidos: any[] = [];
  produtosAlerta: any[] = [];

  // Modal
  produtoSelecionado: any = null;
  quantidadeRepor = 0;

  constructor(
    // private produtoService: ProdutoService,
    // private movimentacaoService: MovimentacaoService,
    // private relatorioService: RelatorioService
  ) {}

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.carregarCards();
    this.carregarMovimentacoes();
    this.carregarMaisVendidos();
    this.carregarProdutosAlerta();
  }

  carregarCards(): void {
    // this.produtoService.listarTodos().subscribe({
    //   next: (data) => {
    //     this.totalProdutos = data.length;
    //     this.valorTotal = data.reduce((sum, p) => sum + (p.quantidade * p.preco), 0);
    //     this.estoqueBaixo = data.filter(p => p.quantidade < 10).length;
    //   }
    // });
  }

  carregarMovimentacoes(): void {
    // this.movimentacaoService.listarTodos().subscribe({
    //   next: (data) => {
    //     this.ultimasMovimentacoes = data.slice(-5).reverse();
    //     this.totalMovimentacoes = data.length;
    //     this.entradas = data.filter(m => m.tipo === 'ENTRADA').length;
    //     this.saidas = data.filter(m => m.tipo === 'SAIDA').length;
    //   }
    // });
  }

  carregarMaisVendidos(): void {
    // this.relatorioService.maisVendidos(5).subscribe({
    //   next: (data) => this.maisVendidos = data
    // });
  }

  carregarProdutosAlerta(): void {
    // this.produtoService.listarTodos().subscribe({
    //   next: (data) => {
    //     this.produtosAlerta = data.filter(p => p.quantidade < 10).slice(0, 5);
    //   }
    // });
  }

  abrirModalEntrada(produto: any): void {
    this.produtoSelecionado = produto;
    this.quantidadeRepor = 0;
    const modal = new (window as any).bootstrap.Modal(document.getElementById('modalReposicao'));
    modal.show();
  }

  reporEstoque(): void {
    if (!this.produtoSelecionado || this.quantidadeRepor <= 0) {
      alert('Digite uma quantidade válida!');
      return;
    }

    // this.produtoService.entradaEstoque(this.produtoSelecionado.id, this.quantidadeRepor).subscribe({
    //   next: () => {
    //     alert('Estoque reposto com sucesso!');
    //     this.carregarDados();
    //     const modal = (window as any).bootstrap.Modal.getInstance(document.getElementById('modalReposicao'));
    //     modal?.hide();
    //   },
    //   error: (err) => {
    //     console.error('Erro ao repor estoque:', err);
    //     alert('Erro ao repor estoque!');
    //   }
    // });
  }
}