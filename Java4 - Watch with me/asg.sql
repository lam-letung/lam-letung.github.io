USE [PolyOEAsg]
GO
ALTER TABLE [dbo].[Shares] DROP CONSTRAINT [FK_Shares_Videos]
GO
ALTER TABLE [dbo].[Shares] DROP CONSTRAINT [FK_Shares_Users]
GO
ALTER TABLE [dbo].[Favorites] DROP CONSTRAINT [FK_Favorites_Videos]
GO
ALTER TABLE [dbo].[Favorites] DROP CONSTRAINT [FK_Favorites_Users]
GO
/****** Object:  Table [dbo].[Videos]    Script Date: 12/04/2022 12:51:34 SA ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Videos]') AND type in (N'U'))
DROP TABLE [dbo].[Videos]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 12/04/2022 12:51:34 SA ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Users]') AND type in (N'U'))
DROP TABLE [dbo].[Users]
GO
/****** Object:  Table [dbo].[Shares]    Script Date: 12/04/2022 12:51:34 SA ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Shares]') AND type in (N'U'))
DROP TABLE [dbo].[Shares]
GO
/****** Object:  Table [dbo].[Favorites]    Script Date: 12/04/2022 12:51:34 SA ******/
IF  EXISTS (SELECT * FROM sys.objects WHERE object_id = OBJECT_ID(N'[dbo].[Favorites]') AND type in (N'U'))
DROP TABLE [dbo].[Favorites]
GO
USE [master]
GO
/****** Object:  Database [PolyOEAsg]    Script Date: 12/04/2022 12:51:34 SA ******/
DROP DATABASE [PolyOEAsg]
GO
/****** Object:  Database [PolyOEAsg]    Script Date: 12/04/2022 12:51:34 SA ******/
CREATE DATABASE [PolyOEAsg]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'PolyOEAsg', FILENAME = N'D:\Desktop\Sql Server\sqlFile\MSSQL15.MSSQLSERVER\MSSQL\DATA\PolyOEAsg.mdf' , SIZE = 8192KB , MAXSIZE = UNLIMITED, FILEGROWTH = 65536KB )
 LOG ON 
( NAME = N'PolyOEAsg_log', FILENAME = N'D:\Desktop\Sql Server\sqlFile\MSSQL15.MSSQLSERVER\MSSQL\DATA\PolyOEAsg_log.ldf' , SIZE = 8192KB , MAXSIZE = 2048GB , FILEGROWTH = 65536KB )
 WITH CATALOG_COLLATION = DATABASE_DEFAULT
GO
ALTER DATABASE [PolyOEAsg] SET COMPATIBILITY_LEVEL = 150
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [PolyOEAsg].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [PolyOEAsg] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [PolyOEAsg] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [PolyOEAsg] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [PolyOEAsg] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [PolyOEAsg] SET ARITHABORT OFF 
GO
ALTER DATABASE [PolyOEAsg] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [PolyOEAsg] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [PolyOEAsg] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [PolyOEAsg] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [PolyOEAsg] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [PolyOEAsg] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [PolyOEAsg] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [PolyOEAsg] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [PolyOEAsg] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [PolyOEAsg] SET  DISABLE_BROKER 
GO
ALTER DATABASE [PolyOEAsg] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [PolyOEAsg] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [PolyOEAsg] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [PolyOEAsg] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [PolyOEAsg] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [PolyOEAsg] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [PolyOEAsg] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [PolyOEAsg] SET RECOVERY FULL 
GO
ALTER DATABASE [PolyOEAsg] SET  MULTI_USER 
GO
ALTER DATABASE [PolyOEAsg] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [PolyOEAsg] SET DB_CHAINING OFF 
GO
ALTER DATABASE [PolyOEAsg] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [PolyOEAsg] SET TARGET_RECOVERY_TIME = 60 SECONDS 
GO
ALTER DATABASE [PolyOEAsg] SET DELAYED_DURABILITY = DISABLED 
GO
ALTER DATABASE [PolyOEAsg] SET ACCELERATED_DATABASE_RECOVERY = OFF  
GO
EXEC sys.sp_db_vardecimal_storage_format N'PolyOEAsg', N'ON'
GO
ALTER DATABASE [PolyOEAsg] SET QUERY_STORE = OFF
GO
USE [PolyOEAsg]
GO
/****** Object:  Table [dbo].[Favorites]    Script Date: 12/04/2022 12:51:34 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Favorites](
	[FavoriteId] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](30) NULL,
	[VideoId] [varchar](50) NULL,
	[LikeDate] [date] NULL,
 CONSTRAINT [PK_Favorites] PRIMARY KEY CLUSTERED 
(
	[FavoriteId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Shares]    Script Date: 12/04/2022 12:51:34 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Shares](
	[ShareId] [int] IDENTITY(1,1) NOT NULL,
	[Username] [varchar](30) NULL,
	[VideoId] [varchar](50) NULL,
	[Email] [nvarchar](250) NULL,
	[ShareDate] [date] NULL,
 CONSTRAINT [PK_Shares] PRIMARY KEY CLUSTERED 
(
	[ShareId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Users]    Script Date: 12/04/2022 12:51:34 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Users](
	[Username] [varchar](30) NOT NULL,
	[Fullname] [nvarchar](50) NOT NULL,
	[Email] [nvarchar](50) NOT NULL,
	[Admin] [bit] NOT NULL,
	[Password] [varchar](20) NOT NULL,
 CONSTRAINT [PK_Users] PRIMARY KEY CLUSTERED 
(
	[Username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Videos]    Script Date: 12/04/2022 12:51:34 SA ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Videos](
	[VideoId] [varchar](50) NOT NULL,
	[Title] [nvarchar](100) NOT NULL,
	[Poster] [nvarchar](150) NOT NULL,
	[Views] [int] NOT NULL,
	[Description] [nvarchar](1500) NULL,
	[Active] [bit] NOT NULL,
 CONSTRAINT [PK_Videos] PRIMARY KEY CLUSTERED 
(
	[VideoId] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO
SET IDENTITY_INSERT [dbo].[Favorites] ON 

INSERT [dbo].[Favorites] ([FavoriteId], [Username], [VideoId], [LikeDate]) VALUES (11, N'lam111', N'v02', CAST(N'2022-04-11' AS Date))
INSERT [dbo].[Favorites] ([FavoriteId], [Username], [VideoId], [LikeDate]) VALUES (12, N'lam111', N'v02', CAST(N'2022-04-11' AS Date))
INSERT [dbo].[Favorites] ([FavoriteId], [Username], [VideoId], [LikeDate]) VALUES (13, N'lam111', N'v02', CAST(N'2022-04-11' AS Date))
INSERT [dbo].[Favorites] ([FavoriteId], [Username], [VideoId], [LikeDate]) VALUES (14, N'lam111', N'v02', CAST(N'2022-04-11' AS Date))
INSERT [dbo].[Favorites] ([FavoriteId], [Username], [VideoId], [LikeDate]) VALUES (15, N'lam111', N'v02', CAST(N'2022-04-11' AS Date))
INSERT [dbo].[Favorites] ([FavoriteId], [Username], [VideoId], [LikeDate]) VALUES (16, N'lam111', N'v02', CAST(N'2022-04-11' AS Date))
INSERT [dbo].[Favorites] ([FavoriteId], [Username], [VideoId], [LikeDate]) VALUES (17, N'lam111', N'v02', CAST(N'2022-04-11' AS Date))
INSERT [dbo].[Favorites] ([FavoriteId], [Username], [VideoId], [LikeDate]) VALUES (18, N'lam111', N'v02', CAST(N'2022-04-11' AS Date))
SET IDENTITY_INSERT [dbo].[Favorites] OFF
GO
SET IDENTITY_INSERT [dbo].[Shares] ON 

INSERT [dbo].[Shares] ([ShareId], [Username], [VideoId], [Email], [ShareDate]) VALUES (1, N'lam111', N'v02', N'lam171ttt@gmail.com', CAST(N'2022-04-10' AS Date))
INSERT [dbo].[Shares] ([ShareId], [Username], [VideoId], [Email], [ShareDate]) VALUES (2, N'lam111', N'v02', N'lam171ttt@gmail.com', CAST(N'2022-04-10' AS Date))
INSERT [dbo].[Shares] ([ShareId], [Username], [VideoId], [Email], [ShareDate]) VALUES (3, N'lam111', N'v02', N'lam171ttt@gmail.com', CAST(N'2022-04-10' AS Date))
INSERT [dbo].[Shares] ([ShareId], [Username], [VideoId], [Email], [ShareDate]) VALUES (5, N'lam111', N'v03', N'lam171ttt@gmail.com', CAST(N'2022-04-11' AS Date))
SET IDENTITY_INSERT [dbo].[Shares] OFF
GO
INSERT [dbo].[Users] ([Username], [Fullname], [Email], [Admin], [Password]) VALUES (N'lam111', N'Le tung lam 1234', N'lam171ttt@gmail.com', 1, N'1234')
GO
INSERT [dbo].[Videos] ([VideoId], [Title], [Poster], [Views], [Description], [Active]) VALUES (N'V01', N'java 2', N'', 1, N'Description', 1)
INSERT [dbo].[Videos] ([VideoId], [Title], [Poster], [Views], [Description], [Active]) VALUES (N'v02', N'jsp 123', N'', 2, N'Description', 1)
INSERT [dbo].[Videos] ([VideoId], [Title], [Poster], [Views], [Description], [Active]) VALUES (N'v03', N'php', N'', 1, N'', 1)
GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Favorites_Users] FOREIGN KEY([Username])
REFERENCES [dbo].[Users] ([Username])
GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK_Favorites_Users]
GO
ALTER TABLE [dbo].[Favorites]  WITH CHECK ADD  CONSTRAINT [FK_Favorites_Videos] FOREIGN KEY([VideoId])
REFERENCES [dbo].[Videos] ([VideoId])
GO
ALTER TABLE [dbo].[Favorites] CHECK CONSTRAINT [FK_Favorites_Videos]
GO
ALTER TABLE [dbo].[Shares]  WITH CHECK ADD  CONSTRAINT [FK_Shares_Users] FOREIGN KEY([Username])
REFERENCES [dbo].[Users] ([Username])
GO
ALTER TABLE [dbo].[Shares] CHECK CONSTRAINT [FK_Shares_Users]
GO
ALTER TABLE [dbo].[Shares]  WITH CHECK ADD  CONSTRAINT [FK_Shares_Videos] FOREIGN KEY([VideoId])
REFERENCES [dbo].[Videos] ([VideoId])
GO
ALTER TABLE [dbo].[Shares] CHECK CONSTRAINT [FK_Shares_Videos]
GO
USE [master]
GO
ALTER DATABASE [PolyOEAsg] SET  READ_WRITE 
GO
